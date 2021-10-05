package pdfWriter;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.log4j.Logger;
import pdfWriter.DTO.InvoiceDTO;
import pdfWriter.DTO.InvoiceDtlDTO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfWriterAsn {
    private static final Logger logger = Logger.getLogger(PdfWriter.class);

    public static void main(String args[]) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(date);
        createAsnPdf();
        System.out.println("Created PDF " + date.toString().substring(10, 19));
    }

    public static PdfPCell insertCell(PdfPTable table, String text, int horAlign, int verAligh, int colSpan, int rowSpan, Font font, BaseColor backgroundColor) {
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        cell.setPaddingTop(2f);
        cell.setPaddingBottom(5f);
        cell.setPaddingLeft(3f);
        cell.setHorizontalAlignment(horAlign);
        if (verAligh > 0) {
            cell.setVerticalAlignment(verAligh);
        }
        cell.setRowspan(rowSpan);
        cell.setColspan(colSpan);
        if (backgroundColor != null)
            cell.setBackgroundColor(backgroundColor);
        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }
        return cell;
    }

    public static void createAsnPdf() {
        // public static void createAsnPdf(InvoiceDtlDTO invoiceDtlDTO, InvoiceDTO invoiceDTO) {
        try {
            Document document = new Document(PageSize.A4, 5f, 30f, 20f, 5f);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("E:/AAD/Documents/Daimler/CreatePdf/ASN_04.pdf"));
            document.open();
            Font fontNormal = new Font(Font.FontFamily.HELVETICA, 10f, Font.BOLD, BaseColor.BLACK);
            Font fontBig = new Font(Font.FontFamily.HELVETICA, 11f, Font.BOLD, BaseColor.BLACK);

            /*11*/
            float[] columnWidths = {13f, 8f, 8f, 8f, 8f, 13f, 8f, 8f, 8f, 10f, 8f};
            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(95f);

            PdfPCell cell = insertCell(table, "PART NO.", Element.ALIGN_LEFT, Element.ALIGN_CENTER, 1, 2, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            /*Part No-ex.A.400.262.09.62*/
//            cell = insertCell(table, invoiceDtlDTO.getPartNo(), Element.ALIGN_LEFT, Element.ALIGN_CENTER, 3, 2, fontBig, null);
            cell = insertCell(table, "A.400.262.09.62", Element.ALIGN_LEFT, Element.ALIGN_CENTER, 3, 2, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            /*Part Barcode*/
            Barcode128 barcodeEAN = new Barcode128();
            barcodeEAN = new Barcode128();
            barcodeEAN.setCodeType(Barcode.CODE128);
//            barcodeEAN.setCode(invoiceDtlDTO.getPartNo());
            barcodeEAN.setCode("A.400.262.09.62");

            barcodeEAN.setFont(null);
            barcodeEAN.setBarHeight(20f);
            barcodeEAN.setX(1f);
            PdfPCell imgBarCode = null;
            try {
                Image codeEANImage = barcodeEAN.createImageWithBarcode(pdfWriter.getDirectContent(), null, null);
                codeEANImage.scalePercent(100);
                codeEANImage.setAlignment(Image.ALIGN_RIGHT);
                imgBarCode = new PdfPCell();
                imgBarCode.addElement(codeEANImage);
                imgBarCode.setVerticalAlignment(Element.ALIGN_CENTER);
                imgBarCode.setRowspan(2);
                imgBarCode.setColspan(5);
                imgBarCode.setPaddingTop(8f);
                imgBarCode.setPaddingRight(10f);
                imgBarCode.setBorder(Rectangle.RIGHT | Rectangle.TOP);
                table.addCell(imgBarCode);
            } catch (Exception e) {
                logger.error(e);
            }

            cell = insertCell(table, "PLANT", Element.ALIGN_LEFT, Element.ALIGN_CENTER, 2, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            cell = insertCell(table, "TRUCK", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 2, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            table.addCell(cell);

            cell = insertCell(table, "PART DESCRIPTION", Element.ALIGN_LEFT, Element.ALIGN_TOP, 5, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            cell = insertCell(table, "ZGS NO.", Element.ALIGN_LEFT, Element.ALIGN_TOP, 1, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            cell = insertCell(table, "KEM NO.", Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            cell = insertCell(table, "INVOICE QTY:", Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            cell = insertCell(table, "THRUST WASHER", Element.ALIGN_CENTER, Element.ALIGN_TOP, 5, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
//            cell.setPaddingBottom(5f);
            table.addCell(cell);

            /*ZES NO*/
            cell = insertCell(table, "", Element.ALIGN_CENTER, Element.ALIGN_TOP, 1, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            table.addCell(cell);

            /*KEM NO*/
            cell = insertCell(table, "", Element.ALIGN_CENTER, Element.ALIGN_TOP, 3, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            table.addCell(cell);

            /*Invoice Qty*/
//    /*hardcoded*/        cell = insertCell(table, "invoiceDtlDTO.getQty()", Element.ALIGN_CENTER, Element.ALIGN_TOP, 2, 1, fontBig, null);
            /*hardcoded*/
            cell = insertCell(table, "10", Element.ALIGN_CENTER, Element.ALIGN_TOP, 2, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            table.addCell(cell);

            /*ASN Number*/
//            cell = insertCell(table, "ASN NO:" + invoiceDTO.getAsnNo(), Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, fontBig, null);
            cell = insertCell(table, "ASN NO:" + "180412173", Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            cell = insertCell(table, "VENDOR CODE", Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            cell = insertCell(table, "PACK QTY.", Element.ALIGN_LEFT, Element.ALIGN_TOP, 1, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            cell = insertCell(table, "REQUIRED PACKS", Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            cell = insertCell(table, "ACTUAL PACKS", Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            /*ASN - BarCode*/
            barcodeEAN = new Barcode128();
            barcodeEAN.setCodeType(Barcode.CODE128);
            barcodeEAN.setCode("180412173");
//            barcodeEAN.setCode(invoiceDTO.getAsnNo());
            barcodeEAN.setFont(null);
            barcodeEAN.setBarHeight(20f);
            barcodeEAN.setX(1f);
            try {
                Image codeEANImage = barcodeEAN.createImageWithBarcode(pdfWriter.getDirectContent(), null, null);
                codeEANImage.scalePercent(90);
                codeEANImage.setAlignment(Image.ALIGN_LEFT);
                imgBarCode = new PdfPCell();
                imgBarCode.setPadding(4f);
                imgBarCode.addElement(codeEANImage);
                imgBarCode.setVerticalAlignment(Element.ALIGN_BOTTOM);
                imgBarCode.setRowspan(1);
                imgBarCode.setColspan(2);
                imgBarCode.setBorder(Rectangle.BOTTOM | Rectangle.LEFT);
                table.addCell(imgBarCode);
            } catch (Exception e) {
                logger.error(e);
            }

            /*Vendor Code*/
            cell = insertCell(table, "15660561", Element.ALIGN_CENTER, Element.ALIGN_TOP, 3, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            /*Packed Qty*/
            cell = insertCell(table, "10", Element.ALIGN_CENTER, Element.ALIGN_TOP, 1, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            /*Required Qty*/
            cell = insertCell(table, "30", Element.ALIGN_CENTER, Element.ALIGN_TOP, 3, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            /*Actual Qty*/
            cell = insertCell(table, "30", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 2, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
            table.addCell(cell);

            cell = insertCell(table, "RTP CODE", Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            cell = insertCell(table, "SA/PO NO.", Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            /*Barcode SA/PO Number*/
            barcodeEAN = new Barcode128();
            barcodeEAN.setCodeType(Barcode.CODE128);
            barcodeEAN.setCode("5500000762");
            barcodeEAN.setFont(null);
            barcodeEAN.setBarHeight(20f);
            barcodeEAN.setX(1f);
            try {
                Image codeEANImage = barcodeEAN.createImageWithBarcode(pdfWriter.getDirectContent(), null, null);
                codeEANImage.scalePercent(100);
                codeEANImage.setAlignment(Image.ALIGN_CENTER);
                imgBarCode = new PdfPCell();
                imgBarCode.addElement(codeEANImage);
                imgBarCode.setVerticalAlignment(Element.ALIGN_BOTTOM);
                imgBarCode.setRowspan(2);
                imgBarCode.setColspan(3);
                imgBarCode.setPaddingBottom(5f);
                imgBarCode.setBorder(Rectangle.BOTTOM);
                table.addCell(imgBarCode);
            } catch (Exception e) {
                logger.error(e);
            }

            cell = insertCell(table, "LABEL NO.", Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            /*Barcode label No*/
            Barcode128 barcodeEANlabelNo = new Barcode128();
            barcodeEAN.setCodeType(Barcode.CODE128);
            barcodeEAN.setCode("1/30");
            barcodeEAN.setFont(null);
            barcodeEAN.setBarHeight(20f);
            barcodeEAN.setX(1f);
            try {
                Image codeEANImage = barcodeEAN.createImageWithBarcode(pdfWriter.getDirectContent(), null, null);
                codeEANImage.scalePercent(100);
                codeEANImage.setAlignment(Image.ALIGN_CENTER);
                imgBarCode = new PdfPCell();
                imgBarCode.addElement(codeEANImage);
                imgBarCode.setVerticalAlignment(Element.ALIGN_BOTTOM);
                imgBarCode.setRowspan(2);
                imgBarCode.setColspan(2);
                imgBarCode.setPaddingBottom(5f);
                imgBarCode.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
                table.addCell(imgBarCode);
            } catch (Exception e) {
                logger.error(e);
            }

            /*RTPCode*/
            cell = insertCell(table, "S.C161.208V30.8", Element.ALIGN_CENTER, Element.ALIGN_TOP, 2, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            /*SA/PO No*/
            cell = insertCell(table, "5500000762", Element.ALIGN_CENTER, Element.ALIGN_TOP, 2, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            /*Label No*/
            cell = insertCell(table, "1/30", Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            cell = insertCell(table, "DELIVERY DATE", Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            cell = insertCell(table, "STORAGE LOCATION", Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            cell = insertCell(table, "INVOICE NO.", Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT);
            cell.setPaddingBottom(2f);
            table.addCell(cell);

            /*Invoice No Barcode*/
            barcodeEAN = new Barcode128();
            barcodeEAN.setCodeType(Barcode.CODE128);
            barcodeEAN.setCode("12345");
//            barcodeEAN.setCode(invoiceDTO.getInvoiceNo());
            barcodeEAN.setFont(null);
            barcodeEAN.setBarHeight(23f);
            barcodeEAN.setX(1f);
            try {
                Image codeEANImage = barcodeEAN.createImageWithBarcode(pdfWriter.getDirectContent(), null, null);
                codeEANImage.scalePercent(85);
                codeEANImage.setAlignment(Image.ALIGN_LEFT);
                imgBarCode = new PdfPCell();
                imgBarCode.setPadding(5f);
                imgBarCode.addElement(codeEANImage);
                imgBarCode.setVerticalAlignment(Element.ALIGN_TOP);
                imgBarCode.setRowspan(2);
                imgBarCode.setColspan(4);
                imgBarCode.setPaddingBottom(0f);
                imgBarCode.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
                table.addCell(imgBarCode);
            } catch (Exception e) {
                e.printStackTrace();
            }

            /*Delivery Date*/
//            cell = insertCell(table, invoiceDTO.getDeliveryDt(), Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, fontBig, null);
            cell = insertCell(table, "12.08.2021", Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            cell.setPaddingLeft(8f);
            table.addCell(cell);

            /*Storage Location*/
            cell = insertCell(table, "MAC01D2-D2", Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            cell.setPaddingLeft(8f);
            table.addCell(cell);

            /*Invoice Number*/
            cell = insertCell(table, "12345", Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, fontBig, null);
//            cell = insertCell(table, invoiceDTO.getInvoiceNo(), Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            cell.setPaddingLeft(8f);
            table.addCell(cell);

            cell = insertCell(table, "SHIP FROM", Element.ALIGN_LEFT, Element.ALIGN_TOP, 4, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            cell.setPaddingBottom(0f);
            table.addCell(cell);

            cell = insertCell(table, "SHIP TO", Element.ALIGN_LEFT, Element.ALIGN_TOP, 4, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            cell.setPaddingBottom(0f);
            table.addCell(cell);

            cell = insertCell(table, "Supplier Part Number", Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 1, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            cell.setPaddingBottom(0f);
            table.addCell(cell);

            /*Ship From Address*/
            cell = insertCell(table, " M.D.INDUSTRIES\n" +
                    "H-28 MIDC AMBAD,,NASIK-422010,India", Element.ALIGN_LEFT, Element.ALIGN_TOP, 4, 3, fontNormal, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            cell.setPaddingBottom(10f);
            table.addCell(cell);

            /*Ship To Address*/
            cell = insertCell(table, "Daimler India Com.Vehicles Pvt.Ltd.\n" +
                    "Oragadam,Sriperumbudur - 602105, India", Element.ALIGN_LEFT, Element.ALIGN_TOP, 4, 3, fontNormal, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            cell.setPaddingBottom(10f);
            table.addCell(cell);

            /*Supplier Part Number*/
            cell = insertCell(table, "", Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 3, fontBig, null);
//            cell = insertCell(table, invoiceDtlDTO.getPartNo(), Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 3, fontBig, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT);
            cell.setPaddingBottom(10f);
            table.addCell(cell);

            document.add(table);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Inside Catch Block");

        }
    }
}
