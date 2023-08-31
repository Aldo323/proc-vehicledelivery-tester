package com.coppel.services.impl;

import com.coppel.dto.ApiResponseDTO;
import com.coppel.dto.BillDTO;
import com.coppel.dto.CtlVehicleDeliveryTransactionalDTO;
import com.coppel.dto.ResponseVehicleDeliveryDTO;
import com.coppel.dto.SerialDTO;
import com.coppel.dto.customerorder.CustomerOrderDTO;
import com.coppel.dto.customerorder.FiscalIssuerDTO;
import com.coppel.dto.customerorder.FiscalReceiverDTO;
import com.coppel.dto.customerorder.StoreDTO;
import com.coppel.dto.customerorder.VehicleDTO;
import com.coppel.dto.invoice.BillInvoiceDTO;
import com.coppel.dto.invoice.InvoiceLetterDTO;
import com.coppel.dto.invoice.VehicleInvoiceDTO;
import com.coppel.dto.repuve.BillRepuveDTO;
import com.coppel.dto.repuve.DeliveryDataDTO;
import com.coppel.dto.repuve.InvoiceRepuveDTO;
import com.coppel.dto.repuve.RepuveProcessDTO;
import com.coppel.dto.repuve.VehicleRepuveDTO;
import com.coppel.entities.Bill;
import com.coppel.entities.CtlVehicleDelivery;
import com.coppel.entities.CtlVehicleDeliveryTransactional;
import com.coppel.entities.MailDeliveryExecutive;
import com.coppel.entities.Serial;
import com.coppel.entities.customerorder.CustomerOrder;
import com.coppel.entities.customerorder.Invoice;
import com.coppel.entities.customerorder.Vehicle;
import com.coppel.entities.invoice.InvoiceRequest;
import com.coppel.entities.pk.CtlVehicleDeliveryPK;
import com.coppel.entities.repuve.RepuveProcess;
import com.coppel.mappers.VehicleDeliveryMapper;
import com.coppel.repositories.CtlVehicleDeliveryTransactionalRepository;
import com.coppel.repositories.MailDeliveryExecutiveRepository;
import com.coppel.repositories.VehicleDeliveryRepository;
import com.coppel.services.VehicleDeliveryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author oscar.pimentel
 */
@Service
public class VehicleDeliveryServiceImpl implements VehicleDeliveryService{

    private static final Logger LOGGER = LogManager.getLogger(VehicleDeliveryServiceImpl.class.getName());
    
    @Autowired
    private Environment env;
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Autowired
    private VehicleDeliveryRepository repositoryVehicle;
    
    @Autowired
    private MailDeliveryExecutiveRepository repositoryMail;
    
    @Autowired
    private CtlVehicleDeliveryTransactionalRepository repositoryDelivery;

    private int region;
    private String orden;
    private String serial;

    @Override
    public ResponseVehicleDeliveryDTO setVehicleDelivery(String orderid, Serial serial) {
        
        Optional<CtlVehicleDeliveryTransactional> nuevaOrden;
        SerialDTO serie= VehicleDeliveryMapper.mapSerial(serial);
        //Validacion de orden y serie
        if(formatCheckout(orderid, serie.getSerial())){
            if(!repositoryDelivery.existsById(orderid)){
                repositoryDelivery.save(new CtlVehicleDeliveryTransactional(orderid, serie.getSerial(), Short.parseShort("0"), Short.parseShort("0"), new Date(System.currentTimeMillis())));
                
                nuevaOrden = repositoryDelivery.findById(orderid);

                return VehicleDeliveryMapper.mapResponseVehicleDelivery(nuevaOrden.get());
            } else{
                LOGGER.log(Level.ERROR, "Ya existe la orden/serie");
                return VehicleDeliveryMapper.mapResponseVehicleDelivery(new CtlVehicleDeliveryTransactional(orderid, serial.getSerie(), Short.parseShort("0"), Short.parseShort("0"), new Date(System.currentTimeMillis())));
            }
        }
        LOGGER.log(Level.ERROR, "Error al procesar la orden/serie");
        throw new IllegalArgumentException("Error al procesar la orden/serie");
    }
    
    @Override
    public Boolean healthz() {
        return true;
    }

    @Override
    public String inicVehicleProcess() {
        
        boolean flagServices = false;
        List<CtlVehicleDeliveryTransactional> ordenesSinProcesar;
        List<CtlVehicleDeliveryTransactionalDTO> ordenesNuevas = new ArrayList<>();
        
        ordenesSinProcesar = repositoryDelivery.findByStatus(Short.parseShort("0"));
        
        for (CtlVehicleDeliveryTransactional ordenSinProcesar : ordenesSinProcesar) {
            ordenesNuevas.add(VehicleDeliveryMapper.mapCtlVehicleDeliveryTransactional(ordenSinProcesar));
        }
        
        
        for (CtlVehicleDeliveryTransactionalDTO ordenNueva : ordenesNuevas) {
            splitOrderId(ordenNueva);
            CustomerOrderDTO customerOrderDto = this.getCustomerOrderData(orden);
            customerOrderDto.setOrderId(orden);
            
            VehicleDTO vehicleDto = this.getVehicleData(serial);
            BillDTO billdto = new BillDTO();
            if(repositoryVehicle.existsById(new CtlVehicleDeliveryPK(orden,Short.parseShort("1")))){
                Optional<CtlVehicleDelivery> facturacion = repositoryVehicle.findById(new CtlVehicleDeliveryPK(orden,Short.parseShort("1")));
                Optional<CtlVehicleDelivery> cartaFactura = repositoryVehicle.findById(new CtlVehicleDeliveryPK(orden,Short.parseShort("2")));
                Optional<CtlVehicleDelivery> repuve = repositoryVehicle.findById(new CtlVehicleDeliveryPK(orden,Short.parseShort("3")));
                
                //SE EJECUTAN LOS METODOS DE LOS SERVICIOS
                //SE MODIFICA flagServices DEPENDIENDO DE LA EJECUCION DE LOS SERVICIOS
                if(facturacion.get().getStatus()!=1){
                    try{
                        billdto=this.processOrderBilling(customerOrderDto);
                        if(billdto==null){
                            facturacion.get().setStatus(Short.parseShort("2"));
                            repositoryVehicle.save(facturacion.get());
                            this.confirmServices(ordenNueva, flagServices);
                            return "";
                        }
                        else{
                            flagServices=true;
                            facturacion.get().setStatus(Short.parseShort("1"));
                            repositoryVehicle.save(facturacion.get());
                        }
                    }catch(Exception e){
                        LOGGER.log(Level.ERROR,Thread.currentThread().getStackTrace()[1].getMethodName(), e);
                        flagServices=false;
                        facturacion.get().setStatus(Short.parseShort("2"));
                        repositoryVehicle.save(facturacion.get());
                        this.confirmServices(ordenNueva, flagServices);
                        return "";
                    }
                }
                
                if(cartaFactura.get().getStatus()!=1){
                    try{
                        InvoiceRequest invoiceRequest = this.getInvoiceRequest(customerOrderDto, VehicleDeliveryMapper.mapVehicleDTO(vehicleDto), VehicleDeliveryMapper.mapBillDTO(billdto));
                        String pdf = this.processInvoiceLetter(invoiceRequest);
                        if(pdf==null){
                            cartaFactura.get().setStatus(Short.parseShort("2"));
                            repositoryVehicle.save(cartaFactura.get());
                            this.confirmServices(ordenNueva, flagServices);
                            return "";
                        }
                        else{
                            flagServices=true;
                            cartaFactura.get().setStatus(Short.parseShort("1"));
                            repositoryVehicle.save(cartaFactura.get());
                        }
                    }catch(Exception e){
                        LOGGER.log(Level.ERROR,Thread.currentThread().getStackTrace()[1].getMethodName(), e);
                        flagServices=false;
                        cartaFactura.get().setStatus(Short.parseShort("2"));
                        repositoryVehicle.save(cartaFactura.get());
                        this.confirmServices(ordenNueva, flagServices);
                        return "";
                    }
                }
                
                if(repuve.get().getStatus()!=1){
                    try{
                        RepuveProcessDTO repuveProcess = this.getRepuveProcess(customerOrderDto, VehicleDeliveryMapper.mapVehicleDTO(vehicleDto),VehicleDeliveryMapper.mapBillDTO(billdto));
                        RepuveProcessDTO repuveResponse=this.processRepuve(repuveProcess);
                        if(repuveProcess == null ||repuveResponse==null){
                            repuve.get().setStatus(Short.parseShort("2"));
                            repositoryVehicle.save(repuve.get());
                            this.confirmServices(ordenNueva, flagServices);
                            return "";
                        }else{
                            flagServices = true;
                            repuve.get().setStatus(Short.parseShort("1"));
                            repositoryVehicle.save(repuve.get());
                        }
                    }catch(Exception e){
                        LOGGER.log(Level.ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), e);
                        flagServices=false;
                        repuve.get().setStatus(Short.parseShort("2"));
                        repositoryVehicle.save(repuve.get());
                        this.confirmServices(ordenNueva, flagServices);
                        return "";
                    }
                }
                

            }else{
                List<CtlVehicleDelivery> ordenesPendientes = new ArrayList<>();
                
                for(int i=0;i<3;i++){
                    ordenesPendientes.add(new CtlVehicleDelivery(new CtlVehicleDeliveryPK(orden,Short.parseShort(""+(i+1))),Short.parseShort("0"),new Date(System.currentTimeMillis())));
                }
                
                repositoryVehicle.saveAll(ordenesPendientes);
                //SE EJECUTAN LOS METODOS DE LOS SERVICIOS
                //SE MODIFICA flagServices DEPENDIENDO DE LA EJECUCION DE LOS SERVICIOS
                //Facturacion electronica
                
                for(int i=0; i<3;i++){
                    switch(i){
                        case 0: {
                            try{
                                billdto=this.processOrderBilling(customerOrderDto);
                                if(billdto==null){
                                    ordenesPendientes.get(i).setStatus(Short.parseShort("2"));
                                    repositoryVehicle.save(ordenesPendientes.get(i));
                                    this.confirmServices(ordenNueva, flagServices);
                                    return "";
                                }
                                else{
                                    flagServices = true;
                                    ordenesPendientes.get(i).setStatus(Short.parseShort("1"));
                                    repositoryVehicle.save(ordenesPendientes.get(i));
                                }
                            }catch(Exception e){
                                LOGGER.log(Level.ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), e);
                                flagServices=false;
                                ordenesPendientes.get(i).setStatus(Short.parseShort("2"));
                                repositoryVehicle.save(ordenesPendientes.get(i));
                                this.confirmServices(ordenNueva, flagServices);
                                return "";
                            }
                            break;
                        }
                        case 1:{
                            try{
                                InvoiceRequest invoiceRequest = this.getInvoiceRequest(customerOrderDto, VehicleDeliveryMapper.mapVehicleDTO(vehicleDto), VehicleDeliveryMapper.mapBillDTO(billdto));
                                String pdf = this.processInvoiceLetter(invoiceRequest);
                                if(pdf==null){
                                    ordenesPendientes.get(i).setStatus(Short.parseShort("2"));
                                    repositoryVehicle.save(ordenesPendientes.get(i));
                                    this.confirmServices(ordenNueva, flagServices);
                                    return "";
                                }
                                else{
                                    flagServices=true;
                                    ordenesPendientes.get(i).setStatus(Short.parseShort("1"));
                                    repositoryVehicle.save(ordenesPendientes.get(i));
                                }
                            }catch(Exception e){
                                LOGGER.log(Level.ERROR,Thread.currentThread().getStackTrace()[1].getMethodName(), e);
                                flagServices=false;
                                ordenesPendientes.get(i).setStatus(Short.parseShort("2"));
                                repositoryVehicle.save(ordenesPendientes.get(i));
                                this.confirmServices(ordenNueva, flagServices);
                                return "";
                            }
                            break;
                        }
                        case 2:{
                            try{
                                RepuveProcessDTO repuveProcess = this.getRepuveProcess(customerOrderDto, VehicleDeliveryMapper.mapVehicleDTO(vehicleDto),VehicleDeliveryMapper.mapBillDTO(billdto));
                                RepuveProcessDTO repuveResponse=this.processRepuve(repuveProcess);
                                if(repuveProcess == null ||repuveResponse==null){
                                    ordenesPendientes.get(i).setStatus(Short.parseShort("2"));
                                    repositoryVehicle.save(ordenesPendientes.get(i));
                                    this.confirmServices(ordenNueva, flagServices);
                                    return "";
                                }else{
                                    flagServices = true;
                                    ordenesPendientes.get(i).setStatus(Short.parseShort("1"));
                                    repositoryVehicle.save(ordenesPendientes.get(i));
                                }
                            }catch(Exception e){
                                LOGGER.log(Level.ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), e);
                                flagServices=false;
                                ordenesPendientes.get(i).setStatus(Short.parseShort("2"));
                                repositoryVehicle.save(ordenesPendientes.get(i));
                                this.confirmServices(ordenNueva, flagServices);
                                return "";
                            }
                            break;
                        }
                        default:{
                                break;
                                }
                    }
                    if(!flagServices){
                        break;
                    }
                }
               
            }   
            this.confirmServices(ordenNueva, flagServices);
        }
        return ""; 
    }
    
    private void confirmServices(CtlVehicleDeliveryTransactionalDTO ordenNueva, boolean flagServices){
        if(!flagServices){
                if(ordenNueva.getAttempt()==1){
                    Short attempt = 2;
                    Short status = 2;
                    ordenNueva.setAttempt(attempt);
                    ordenNueva.setStatus(status);

                    repositoryDelivery.save(VehicleDeliveryMapper.mapCtlVehicleDeliveryTransactionalDTO(ordenNueva));
                    final Optional<MailDeliveryExecutive> mail=repositoryMail.findById(region);
                    String correo = "Se envio error al correo "+mail.get().getEmail()+" del ejecutivo "+mail.get().getName();
                    LOGGER.log(Level.ERROR, "Error al procesar orden:{0} ",correo);
                } else{
                    Short attempt = ordenNueva.getAttempt();
                    attempt++;
                    ordenNueva.setAttempt(attempt);
                    repositoryDelivery.save(VehicleDeliveryMapper.mapCtlVehicleDeliveryTransactionalDTO(ordenNueva));
                }
            }
            else{
                Short status = 1;
                ordenNueva.setStatus(status);
                repositoryDelivery.save(VehicleDeliveryMapper.mapCtlVehicleDeliveryTransactionalDTO(ordenNueva));
            }
    }
    
    private InvoiceRequest getInvoiceRequest(CustomerOrderDTO customerOrderDto, Vehicle vehicle, Bill bill){
        Invoice invoice = VehicleDeliveryMapper.mapInvoiceDTO(customerOrderDto.getInvoice());
        
        StoreDTO store = customerOrderDto.getStore();
        FiscalIssuerDTO fiscalIssuer = customerOrderDto.getFiscalIssuer();
        FiscalReceiverDTO fiscalReceiver = customerOrderDto.getFiscalReceiver();
        VehicleInvoiceDTO vehicleInvoice = VehicleDeliveryMapper.mapVehicleInvoice(vehicle);
        InvoiceLetterDTO invoiceLetter = VehicleDeliveryMapper.mapInvoiceLetter(invoice);
        BillInvoiceDTO billInvoice = VehicleDeliveryMapper.mapBillInvoice(bill);

        return new InvoiceRequest(store,fiscalIssuer,fiscalReceiver,vehicleInvoice,invoiceLetter,billInvoice);
    }
    
    private String processInvoiceLetter(InvoiceRequest invoiceRequest){
        
       String pdf;
        try {
            URI uri = new URI(env.getProperty("com-cap-invoiceletter:"));
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ApiResponseDTO> response = restTemplate.postForEntity(uri, invoiceRequest, ApiResponseDTO.class);
            
            ApiResponseDTO apiResponseDTO = response.getBody();
            assert apiResponseDTO != null;
            JsonNode dataNode = objectMapper.valueToTree(apiResponseDTO.getData());
            pdf = objectMapper.readValue(dataNode.toString(), new TypeReference<>() {});
            
        } catch (URISyntaxException ex) {
            LOGGER.log(Level.ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            throw new IllegalArgumentException("Error al consumir el servicio com-cap-invoiceletter", ex);
        } catch (JsonProcessingException ex) {
            LOGGER.log(Level.ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            throw new IllegalArgumentException("Error al realizar la carta factura con el servicio com-cap-invoiceletter", ex);
        }
        return pdf;
    }
    
    private RepuveProcessDTO getRepuveProcess(CustomerOrderDTO customerOrderDto,Vehicle vehicle,Bill bill){
        RepuveProcessDTO repuveProcess = new RepuveProcessDTO();
        
        DeliveryDataDTO delivery = new DeliveryDataDTO();

        delivery.setActivationDate(new Date(System.currentTimeMillis()));
        delivery.setAssortmentDate(new Date(System.currentTimeMillis()));
        delivery.setDeliveryDate(new Date(System.currentTimeMillis()));
        delivery.setMovementDate(new Date(System.currentTimeMillis()));

        VehicleRepuveDTO vehicleRepuve = VehicleDeliveryMapper.mapVehicleRepuve(vehicle);
                

        Invoice invoice=VehicleDeliveryMapper.mapInvoiceDTO(customerOrderDto.getInvoice());
        
        InvoiceRepuveDTO invoiceRepuve = VehicleDeliveryMapper.mapInvoiceRepuve(invoice);
        
        
        BillRepuveDTO billRepuve = VehicleDeliveryMapper.mapBillRepuve(bill);

        repuveProcess.setOrderId(orden);
        repuveProcess.setDelivery(delivery);
        repuveProcess.setVehicle(vehicleRepuve);
        repuveProcess.setInvoice(invoiceRepuve);
        repuveProcess.setBill(billRepuve);
        
        return repuveProcess;
    }
    
    private RepuveProcessDTO processRepuve(RepuveProcessDTO repuveProcessDto){
        
        RepuveProcess repuve;
        RepuveProcessDTO repuveDto = new RepuveProcessDTO();
        try {
            URI uri = new URI(env.getProperty("proc-repuvedata"));
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ApiResponseDTO> response = restTemplate.postForEntity(uri, repuveProcessDto, ApiResponseDTO.class);
            
            ApiResponseDTO apiResponseDTO = response.getBody();
            assert apiResponseDTO != null;
            JsonNode dataNode = objectMapper.valueToTree(apiResponseDTO.getData());
            repuve = objectMapper.readValue(dataNode.toString(), new TypeReference<>() {});
            repuveDto.setOrderId(repuve.getOrderId());
            repuveDto.setDelivery(VehicleDeliveryMapper.mapDeliveryData(repuve.getDelivery()));
            repuveDto.setInvoice(VehicleDeliveryMapper.mapInvoiceRepuve(repuve.getInvoice()));
            repuveDto.setVehicle(VehicleDeliveryMapper.mapVehicleRepuve(repuve.getVehicle()));
            repuveDto.setBill(VehicleDeliveryMapper.mapBillRepuve(repuve.getBill()));
        } catch (URISyntaxException ex) {
            LOGGER.log(Level.ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            throw new IllegalArgumentException("Error al consumir el servicio proc-orderbilling", ex);
        } catch (JsonProcessingException ex) {
            LOGGER.log(Level.ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            throw new IllegalArgumentException("Error al llenar la entidad Bill con el servicio proc-orderbilling", ex);
        }
        return repuveDto;
    }
    
    private BillDTO processOrderBilling(CustomerOrderDTO billing){

        Bill bill;
        BillDTO billDto;
        try {
            URI uri = new URI(env.getProperty("proc-orderbilling"));
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ApiResponseDTO> response = restTemplate.postForEntity(uri, billing, ApiResponseDTO.class);
            
            ApiResponseDTO apiResponseDTO = response.getBody();
            assert apiResponseDTO != null;
            JsonNode dataNode = objectMapper.valueToTree(apiResponseDTO.getData());
            bill = objectMapper.readValue(dataNode.toString(), new TypeReference<>() {});
            billDto= VehicleDeliveryMapper.mapBill(bill);
        } catch (URISyntaxException ex) {
            LOGGER.log(Level.ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            throw new IllegalArgumentException("Error al consumir el servicio proc-orderbilling", ex);
        } catch (JsonProcessingException ex) {
            LOGGER.log(Level.ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            throw new IllegalArgumentException("Error al llenar la entidad Bill con el servicio proc-orderbilling", ex);
        }
        return billDto;
    }
    
    private CustomerOrderDTO getCustomerOrderData(String orderid){

        CustomerOrder customerOrder;
        CustomerOrderDTO customerOrderDto;
        try {
            URI uri = new URI(env.getProperty("com-sys-order")+orderid);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ApiResponseDTO> response = restTemplate.getForEntity(uri, ApiResponseDTO.class);
            
            ApiResponseDTO apiResponseDTO = response.getBody();
            assert apiResponseDTO != null;
            JsonNode dataNode = objectMapper.valueToTree(apiResponseDTO.getData());
            customerOrder = objectMapper.readValue(dataNode.toString(), new TypeReference<>() {});
            customerOrderDto= VehicleDeliveryMapper.mapCustomerOrder(customerOrder);
        } catch (URISyntaxException ex) {
            LOGGER.log(Level.ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            throw new IllegalArgumentException("Error al consumir el servicio com-sys-order", ex);
        } catch (JsonProcessingException ex) {
            LOGGER.log(Level.ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            throw new IllegalArgumentException("Error al llenar la entidad CustomerOrder con el servicio com-sys-order", ex);
        }
        return customerOrderDto;
    }
    
    private VehicleDTO getVehicleData(String serie){

        Vehicle vehicle;
        VehicleDTO vehicleDto;
        try {
            URI uri = new URI(env.getProperty("com-sys-orderVehicle")+serie);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ApiResponseDTO> response = restTemplate.getForEntity(uri, ApiResponseDTO.class);
            
            ApiResponseDTO apiResponseDTO = response.getBody();
            assert apiResponseDTO != null;
            JsonNode dataNode = objectMapper.valueToTree(apiResponseDTO.getData());
            vehicle = objectMapper.readValue(dataNode.toString(), new TypeReference<>() {});
            vehicleDto= VehicleDeliveryMapper.mapVehicle(vehicle);
        } catch (URISyntaxException ex) {
            LOGGER.log(Level.ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            throw new IllegalArgumentException("Error al consumir el servicio com-sys-order", ex);
        } catch (JsonProcessingException ex) {
            LOGGER.log(Level.ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            throw new IllegalArgumentException("Error al llenar la entidad Vehicle con el servicio com-sys-order", ex);
        }
        return vehicleDto;
    }
    
    private boolean formatCheckout(String orderid, String serie){
        
        //Validaciones del numero de serie
        for(int i=0;i<serie.length();i++){
            if(!(Character.isDigit(serie.charAt(i))||Character.isLetter(serie.charAt(i)))){
                return false;
            }
        }
        
        //Validaciones del orderid
        
        if(orderid.length()!=36){
            return false;
        } else {
            if(splitVerification(orderid)){
                return true;
            }
        }
            
        return false;
        
    }
    
    private void splitOrderId(CtlVehicleDeliveryTransactionalDTO ordenNueva) {
        orden=ordenNueva.getOrden();
        String[] datos = ordenNueva.getOrden().split("-");
        region = Integer.parseInt(datos[0]);
        serial = ordenNueva.getSerial();
        
    }
    
    private boolean splitVerification(String orden){
        String[] datos = orden.split("-");
        for (String dato : datos) {
            for(int i=0;i<dato.length();i++){
                if(!(Character.isDigit(dato.charAt(i))||Character.isLetter(dato.charAt(i)))){
                    return false;
                }
            }
        }
        return !(datos[0].length()!=5||datos[1].length()!=1||datos[2].length()!=2||datos[3].length()!=5||datos[4].length()!=10||datos[5].length()!=8);
 
    } 
}
