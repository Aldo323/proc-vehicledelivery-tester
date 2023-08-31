package com.coppel.mappers;

import com.coppel.dto.BillDTO;
import com.coppel.dto.CtlVehicleDeliveryDTO;
import com.coppel.dto.CtlVehicleDeliveryTransactionalDTO;
import com.coppel.dto.MailDeliveryExecutiveDTO;
import com.coppel.dto.ResponseVehicleDeliveryDTO;
import com.coppel.dto.SerialDTO;
import com.coppel.dto.customerorder.AddressStoreDTO;
import com.coppel.dto.customerorder.CustomerOrderDTO;
import com.coppel.dto.customerorder.FiscalIssuerAddressDTO;
import com.coppel.dto.customerorder.FiscalIssuerDTO;
import com.coppel.dto.customerorder.FiscalReceiverAddressDTO;
import com.coppel.dto.customerorder.FiscalReceiverDTO;
import com.coppel.dto.customerorder.InvoiceDTO;
import com.coppel.dto.customerorder.StoreDTO;
import com.coppel.dto.customerorder.VehicleDTO;
import com.coppel.dto.invoice.BillInvoiceDTO;
import com.coppel.dto.invoice.InvoiceLetterDTO;
import com.coppel.dto.invoice.VehicleInvoiceDTO;
import com.coppel.dto.repuve.BillRepuveDTO;
import com.coppel.dto.repuve.DeliveryDataDTO;
import com.coppel.dto.repuve.InvoiceRepuveDTO;
import com.coppel.dto.repuve.VehicleRepuveDTO;
import com.coppel.entities.Bill;
import com.coppel.entities.CtlVehicleDelivery;
import com.coppel.entities.CtlVehicleDeliveryTransactional;
import com.coppel.entities.MailDeliveryExecutive;
import com.coppel.entities.Serial;
import com.coppel.entities.customerorder.AddressStore;
import com.coppel.entities.customerorder.CustomerOrder;
import com.coppel.entities.customerorder.FiscalIssuer;
import com.coppel.entities.customerorder.FiscalIssuerAddress;
import com.coppel.entities.customerorder.FiscalReceiver;
import com.coppel.entities.customerorder.FiscalReceiverAddress;
import com.coppel.entities.customerorder.Invoice;
import com.coppel.entities.customerorder.Store;
import com.coppel.entities.customerorder.Vehicle;
import com.coppel.entities.repuve.DeliveryData;
import org.modelmapper.ModelMapper;

/**
 *
 * @author oscar.pimentel
 */
public class VehicleDeliveryMapper {
    
    private VehicleDeliveryMapper() {
        throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    
    public static CtlVehicleDeliveryDTO mapCtlVehicleDelivery(CtlVehicleDelivery ctlVehicleDelivery) {
        return MAPPER.map(ctlVehicleDelivery, CtlVehicleDeliveryDTO.class);
    }

    public static CtlVehicleDelivery mapCtlVehicleDeliveryDTO(CtlVehicleDeliveryDTO ctlVehicleDeliveryDTO) {
        return MAPPER.map(ctlVehicleDeliveryDTO, CtlVehicleDelivery.class);
    }
    
    public static MailDeliveryExecutiveDTO mapMailDeliveryExecutive(MailDeliveryExecutive mailDeliveryExecutive) {
        return MAPPER.map(mailDeliveryExecutive, MailDeliveryExecutiveDTO.class);
    }

    public static MailDeliveryExecutive mapMailDeliveryExecutiveDTO(MailDeliveryExecutiveDTO mailDeliveryExecutiveDTO) {
        return MAPPER.map(mailDeliveryExecutiveDTO, MailDeliveryExecutive.class);
    }
    
    public static CtlVehicleDeliveryTransactionalDTO mapCtlVehicleDeliveryTransactional(CtlVehicleDeliveryTransactional ctlVehicleDeliveryTransactional) {
        return MAPPER.map(ctlVehicleDeliveryTransactional, CtlVehicleDeliveryTransactionalDTO.class);
    }

    public static CtlVehicleDeliveryTransactional mapCtlVehicleDeliveryTransactionalDTO(CtlVehicleDeliveryTransactionalDTO ctlVehicleDeliveryTransactionalDTO) {
        return MAPPER.map(ctlVehicleDeliveryTransactionalDTO, CtlVehicleDeliveryTransactional.class);
    }
    
    
    public static ResponseVehicleDeliveryDTO mapResponseVehicleDelivery(CtlVehicleDeliveryTransactional ctlVehicleDeliveryTransactional) {
        return MAPPER.map(ctlVehicleDeliveryTransactional, ResponseVehicleDeliveryDTO.class);
    }

    public static CtlVehicleDeliveryTransactional mapResponseVehicleDeliveryDTO(ResponseVehicleDeliveryDTO responseVehicleDeliveryDTO) {
        return MAPPER.map(responseVehicleDeliveryDTO, CtlVehicleDeliveryTransactional.class);
    }
    
    public static BillDTO mapBill(Bill bill) {
        return MAPPER.map(bill, BillDTO.class);
    }

    public static Bill mapBillDTO(BillDTO billDTO) {
        return MAPPER.map(billDTO, Bill.class);
    }
    
    public static SerialDTO mapSerial(Serial serial) {
        return MAPPER.map(serial, SerialDTO.class);
    }

    public static Serial mapSerialDTO(SerialDTO serialDTO) {
        return MAPPER.map(serialDTO, Serial.class);
    }
    
    public static CustomerOrderDTO mapCustomerOrder(CustomerOrder customerOrder) {
        return MAPPER.map(customerOrder, CustomerOrderDTO.class);
    }

    public static CustomerOrder mapCustomerOrderDTO(CustomerOrderDTO customerOrderDTO) {
        return MAPPER.map(customerOrderDTO, CustomerOrder.class);
    }
    
    public static VehicleDTO mapVehicle(Vehicle vehicle) {
        return MAPPER.map(vehicle, VehicleDTO.class);
    }

    public static Vehicle mapVehicleDTO(VehicleDTO vehicleDTO) {
        return MAPPER.map(vehicleDTO, Vehicle.class);
    }
    
    public static DeliveryDataDTO mapDeliveryData(DeliveryData deliveryData) {
        return MAPPER.map(deliveryData, DeliveryDataDTO.class);
    }

    public static DeliveryData mapDeliveryDataDTO(DeliveryDataDTO deliveryDataDTO) {
        return MAPPER.map(deliveryDataDTO, DeliveryData.class);
    }
    
    public static VehicleRepuveDTO mapVehicleRepuve(Vehicle vehicleRepuve) {
        return MAPPER.map(vehicleRepuve, VehicleRepuveDTO.class);
    }

    public static Vehicle mapVehicleRepuveDTO(VehicleRepuveDTO vehicleRepuveDTO) {
        return MAPPER.map(vehicleRepuveDTO, Vehicle.class);
    }
    
    public static InvoiceRepuveDTO mapInvoiceRepuve(Invoice invoiceRepuve) {
        return MAPPER.map(invoiceRepuve, InvoiceRepuveDTO.class);
    }

    public static Invoice mapInvoiceRepuveDTO(InvoiceRepuveDTO invoiceRepuveDTO) {
        return MAPPER.map(invoiceRepuveDTO, Invoice.class);
    }
    
    public static BillRepuveDTO mapBillRepuve(Bill billRepuve) {
        return MAPPER.map(billRepuve, BillRepuveDTO.class);
    }

    public static Bill mapBillRepuveDTO(BillRepuveDTO billRepuveDTO) {
        return MAPPER.map(billRepuveDTO, Bill.class);
    }
    
    public static StoreDTO mapStore(Store store) {
        return MAPPER.map(store, StoreDTO.class);
    }

    public static Store mapStoreDTO(StoreDTO storeDTO) {
        return MAPPER.map(storeDTO, Store.class);
    }
    
    public static AddressStoreDTO mapStoreAddress(AddressStore store) {
        return MAPPER.map(store, AddressStoreDTO.class);
    }

    public static AddressStore mapStoreAddressDTO(AddressStoreDTO addressStoreDTO) {
        return MAPPER.map(addressStoreDTO, AddressStore.class);
    }
    
    public static InvoiceDTO mapInvoice(Invoice invoice) {
        return MAPPER.map(invoice, InvoiceDTO.class);
    }

    public static Invoice mapInvoiceDTO(InvoiceDTO invoiceDTO) {
        return MAPPER.map(invoiceDTO, Invoice.class);
    }
    
    public static FiscalIssuerDTO mapFiscalIssuer(FiscalIssuer fiscalIssuer) {
        return MAPPER.map(fiscalIssuer, FiscalIssuerDTO.class);
    }

    public static FiscalIssuer mapFiscalIssuerDTO(FiscalIssuerDTO fiscalIssuerDTO) {
        return MAPPER.map(fiscalIssuerDTO, FiscalIssuer.class);
    }
    
    public static FiscalIssuerAddressDTO mapFiscalIssuerAddress(FiscalIssuerAddress fiscalIssuerAddress) {
        return MAPPER.map(fiscalIssuerAddress, FiscalIssuerAddressDTO.class);
    }

    public static FiscalIssuerAddress mapFiscalIssuerAddressDTO(FiscalIssuerAddressDTO fiscalIssuerAddressDTO) {
        return MAPPER.map(fiscalIssuerAddressDTO, FiscalIssuerAddress.class);
    }
    
    public static FiscalReceiverDTO mapFiscalReceiver(FiscalReceiver fiscalreceiver) {
        return MAPPER.map(fiscalreceiver, FiscalReceiverDTO.class);
    }

    public static FiscalReceiver mapFiscalReceiverDTO(FiscalReceiverDTO fiscalreceiverDTO) {
        return MAPPER.map(fiscalreceiverDTO, FiscalReceiver.class);
    }

    public static FiscalReceiverAddressDTO mapFiscalReceiverAddress(FiscalReceiverAddress fiscalReceiverAddress) {
        return MAPPER.map(fiscalReceiverAddress, FiscalReceiverAddressDTO.class);
    }

    public static FiscalReceiverAddress mapFiscalReceiverAddressDTO(FiscalReceiverAddressDTO fiscalReceiverAddressDTO) {
        return MAPPER.map(fiscalReceiverAddressDTO, FiscalReceiverAddress.class);
    }
    
    public static VehicleInvoiceDTO mapVehicleInvoice(Vehicle vehicle) {
        return MAPPER.map(vehicle, VehicleInvoiceDTO.class);
    }

    public static Vehicle mapVehicleInvoiceDTO(VehicleInvoiceDTO vehicleInvoiceDTO) {
        return MAPPER.map(vehicleInvoiceDTO, Vehicle.class);
    }
    
    
    public static InvoiceLetterDTO mapInvoiceLetter(Invoice invoice) {
        return MAPPER.map(invoice, InvoiceLetterDTO.class);
    }

    public static Invoice mapInvoiceLetterDTO(InvoiceLetterDTO invoiceLetterDTO) {
        return MAPPER.map(invoiceLetterDTO, Invoice.class);
    }
    
    public static BillInvoiceDTO mapBillInvoice(Bill bill) {
        return MAPPER.map(bill, BillInvoiceDTO.class);
    }

    public static Bill mapBillInvoiceDTO(BillInvoiceDTO billInvoiceDTO) {
        return MAPPER.map(billInvoiceDTO, Bill.class);
    }

}
