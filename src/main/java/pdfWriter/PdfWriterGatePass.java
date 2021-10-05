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

public class PdfWriterGatePass {

    private static final Logger logger = Logger.getLogger(PdfWriter.class);

    public static void main(String args[]) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(date);
        createGatePass();
        System.out.println("Created PDF " + date.toString().substring(10, 19));
    }

    public static PdfPCell insertCell(PdfPTable table, String text, int horAlign, int verAligh, int colSpan, int rowSpan, Font font, BaseColor backgroundColor) {
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        cell.setPaddingTop(2f);
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

    public static void createGatePass() {
   // public static void createGatePass(InvoiceDTO invoiceDTO,InvoiceDtlDTO invoiceDtlDTO) {
        try {
            Document document = new Document(PageSize.A4.rotate(), 5f, 5f, 5f, 5f);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("E:/AAD/Documents/Daimler/CreatePdf/GatePass.pdf"));
            document.open();
            Font fontNormal = new Font(Font.FontFamily.HELVETICA, 10f, Font.NORMAL, BaseColor.BLACK);
            Font fontBold = new Font(Font.FontFamily.HELVETICA, 10f, Font.BOLD, BaseColor.BLACK);
            Font fontHeader = new Font(Font.FontFamily.HELVETICA, 14f, Font.BOLD, BaseColor.BLACK);
            /*13*/
            float[] columnWidths = {11f, 7f, 10f, 11f, 3f, 6f, 6f, 3f, 7f, 8f, 8f, 7f, 13};
            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(95f);

            PdfPCell cell = insertCell(table, "Goods Verification Note / Gate Pass", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 13, 2, fontHeader, null);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPaddingBottom(15f);
            table.addCell(cell);

            cell = insertCell(table, "Vendor Details:", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 13, 2, fontBold, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
            table.addCell(cell);

            cell = insertCell(table, "MOTHERSON AUTOMOTIVE TECHNOLOGIES &\n" +
                    "PONDUR VILLAGE & POST KANCHEEPURAM DISTRICT 602105\n" +
                    "India Tamil Nadu", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 13, 3, fontNormal, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            cell.setPaddingBottom(12f);
            table.addCell(cell);

            cell = insertCell(table, "Unloading Dock No: ", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 2, 2, fontBold, null);
            cell.setBorder(Rectangle.LEFT);
            cell.setPaddingBottom(9f);
            table.addCell(cell);

            /*Unloading Dock NO*/
            cell = insertCell(table, " ", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 11, 2, fontBold, null);
            cell.setBorder(Rectangle.RIGHT);
            table.addCell(cell);

            cell = insertCell(table, "Vehicle Number: ", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 2, fontBold, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            /*Vechicle Number*/
            cell = insertCell(table, "TN88H3906 ", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 2, 2, fontBold, null);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            Barcode128 barcodeEAN = new Barcode128();
            barcodeEAN.setCodeType(Barcode.CODE128);
//            barcodeEAN.setCode(invoiceDTO.getTruckNo());
            barcodeEAN.setCode("TN88H3906");
            barcodeEAN.setFont(null);
            barcodeEAN.setBarHeight(25f);
            barcodeEAN.setX(1f);
            PdfPCell imgBarCode = null;
            try {
                Image codeEANImage = barcodeEAN.createImageWithBarcode(pdfWriter.getDirectContent(), null, null);
                codeEANImage.scalePercent(100);
                codeEANImage.setAlignment(Image.ALIGN_LEFT);
                imgBarCode = new PdfPCell();
                imgBarCode.addElement(codeEANImage);
                imgBarCode.setVerticalAlignment(Element.ALIGN_TOP);
                imgBarCode.setRowspan(2);
                imgBarCode.setColspan(10);
                imgBarCode.setBorder(Rectangle.RIGHT | Rectangle.BOTTOM);
                imgBarCode.setPaddingBottom(8f);
                table.addCell(imgBarCode);
            } catch (Exception e) {
                logger.error(e);
            }

            cell = insertCell(table, "Doc. No ", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontBold, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT);
            table.addCell(cell);

            cell = insertCell(table, "Item", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontBold, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT);
            table.addCell(cell);

            cell = insertCell(table, "Invoice No", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontBold, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT);
            table.addCell(cell);

            cell = insertCell(table, "Material No", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontBold, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT);
            table.addCell(cell);

            cell = insertCell(table, "QC", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontBold, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT);
            table.addCell(cell);

            cell = insertCell(table, "Quantity", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontBold, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT);
            table.addCell(cell);

            cell = insertCell(table, "Unit", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontBold, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT);
            table.addCell(cell);

            cell = insertCell(table, "RTP Code / Pack Quantity", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 2, 1, fontBold, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT);
            table.addCell(cell);

            cell = insertCell(table, "Actual Qty", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontBold, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT);
            table.addCell(cell);

            cell = insertCell(table, "No of Boxes", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontBold, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT);
            table.addCell(cell);

            cell = insertCell(table, "Qty / Box", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontBold, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT);
            table.addCell(cell);

            cell = insertCell(table, "Barcode", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontNormal, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.RIGHT);
            table.addCell(cell);

            /*Dock No*/
            cell = insertCell(table, "182735113", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontNormal, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            /*Item*/
            cell = insertCell(table, "000010", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontNormal, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            /*Invoice Number*/
            cell = insertCell(table,"1821507573" , Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontNormal, null);
//            cell = insertCell(table,invoiceDTO.getInvoiceNo() , Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontBold, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            /*Material No*/
            cell = insertCell(table, "A.400.462.02.23", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontNormal, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            /*QC*/
            cell = insertCell(table, " ", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontNormal, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            /*Quantity*/
//            cell = insertCell(table, invoiceDtlDTO.getQty(), Element.ALIGN_LEFT, Element.ALIGN_RIGHT, 1, 1, fontBold, null);
            cell = insertCell(table, "6", Element.ALIGN_RIGHT, Element.ALIGN_LEFT, 1, 1, fontNormal, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            /*Unit*/
            cell = insertCell(table, "PC", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontNormal, null);
//            cell = insertCell(table, invoiceDtlDTO.getUom(), Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontBold, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            /*RTP Code*/
            cell = insertCell(table, "/ 6 ", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 2, 1, fontNormal, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            /*Actual Qty*/
            cell = insertCell(table, " ", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontNormal, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            /*No of Boxes*/
            cell = insertCell(table, " ", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontNormal, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            /*Qty/Box*/
            cell = insertCell(table, " ", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 1, fontNormal, null);
            cell.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
            table.addCell(cell);

            /*Invoice Barcode*/
            barcodeEAN = new Barcode128();
            barcodeEAN.setCodeType(Barcode.CODE128);
//            barcodeEAN.setCode(invoiceDTO.getInvoiceNo());
           barcodeEAN.setCode("1821507553");
            barcodeEAN.setFont(null);
            barcodeEAN.setBarHeight(18f);
            barcodeEAN.setX(1f);
            try {
                Image codeEANImage = barcodeEAN.createImageWithBarcode(pdfWriter.getDirectContent(), null, null);
                codeEANImage.scalePercent(100);
                codeEANImage.setAlignment(Image.ALIGN_CENTER);
                imgBarCode = new PdfPCell();
                imgBarCode.setPadding(5f);
                imgBarCode.addElement(codeEANImage);
                imgBarCode.setVerticalAlignment(Element.ALIGN_BOTTOM);
                imgBarCode.setRowspan(1);
                imgBarCode.setColspan(1);
                imgBarCode.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
                table.addCell(imgBarCode);
            } catch (Exception e) {
                logger.error(e);
            }

            cell = insertCell(table, "Remarks ", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 1, 3, fontNormal, null);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPaddingTop(10f);
            cell.setPaddingBottom(10f);
            table.addCell(cell);

            /*Remarks*/
            cell = insertCell(table, " ", Element.ALIGN_LEFT, Element.ALIGN_LEFT, 12, 3, fontNormal, null);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            document.add(table);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Inside Catch Block");

        }
    }
}
