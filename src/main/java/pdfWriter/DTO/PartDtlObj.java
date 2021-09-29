package pdfWriter.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartDtlObj
{
    private String partNo;
    private String dispPartNo;
    private String shipTo;
    private String icShipTo;
    private String partDesc;
    private String prodPartNo;
    private int packQty;
    private double sgsTax;
    private double cgsTax;
    private double igsTax;
    private String unit;
    private double unitPrice;
    private double focPrice;
    private double taPrice;
    private String strLoc1;
    private String strLoc2;
    private double boxWt;
    private double grossWt;
    private String container;
    private int trolleySize;

    private String serialNo;
    private String partSerialNos;

    private String requestType;
    private int plantId;

    private double invAmt;
    private double taxableAmt;
    private double sgstAmt;
    private double cgstAmt;
    private double igstAmt;
    private double amt;
    private int quantity;

    private String dockNo;


}
