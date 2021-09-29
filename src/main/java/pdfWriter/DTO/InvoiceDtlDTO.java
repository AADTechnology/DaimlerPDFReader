package pdfWriter.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceDtlDTO {
    private String invoiceKey;//OemId_PlantId_InvoiceNo
    private String partNo;
    private String scheduleAgmt;
    private String lineItem;
    private String uom;
    private int qty;
    private String rtpMtl;
}
