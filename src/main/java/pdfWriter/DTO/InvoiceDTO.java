package pdfWriter.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class InvoiceDTO {
    private String id;
    private String invoiceNo;

    private String deliveryDt; //YYYYMMDD
    private String invoiceDt;
    private String shipDt;
    private String truckNo;
    private String pickListNo;

    private String asnNo;
    private String preGVNNo;

    private int status;

    List<InvoiceDtlDTO> invoiceDtlDTOS;
}
