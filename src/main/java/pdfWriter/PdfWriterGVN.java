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

public class PdfWriterGVN {

    private static final Logger logger = Logger.getLogger(PdfWriter.class);

    public static void main(String args[]) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(date);
        createGrnPdf();
        System.out.println("Created PDF " + date.toString().substring(10, 19));
    }

    public static PdfPCell insertCell(PdfPTable table, String text, int horAlign, int verAligh, int colSpan, int rowSpan, Font font, BaseColor backgroundColor) {
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        cell.setPaddingTop(10f);
        cell.setPaddingBottom(10f);
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

//    public static void createGrnPdf(InvoiceDtlDTO invoiceDtlDTO, InvoiceDTO invoiceDTO) {
   public static void createGrnPdf() {

        try {
            Document document = new Document(PageSize.A4, 5f, 30f, 20f, 5f);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("E:/AAD/Documents/Daimler/CreatePdf/Gvn_04.pdf"));
            document.open();
            Font fontNormal = new Font(Font.FontFamily.HELVETICA, 11f, Font.NORMAL, BaseColor.BLACK);
            Font fontBold = new Font(Font.FontFamily.HELVETICA, 11f, Font.BOLD, BaseColor.BLACK);
            Font fontHeader = new Font(Font.FontFamily.HELVETICA, 12f, Font.BOLD, BaseColor.WHITE);
            BaseColor backgroundDark = new BaseColor(141, 143, 145);
            BaseColor backgroundLight = new BaseColor(190, 193, 196);
            /*7*/
            float[] columnWidths = {12f, 10f, 18f, 12f, 13f, 17f, 19f};
            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(95f);


            PdfPCell cell = insertCell(table, "DICV Pre-GVN Document", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 7, 3, fontHeader, backgroundDark);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            table.addCell(cell);

            cell = insertCell(table, "Vendor", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 2, 1, fontBold, backgroundLight);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            table.addCell(cell);

            /*Vendor Code*/
            cell = insertCell(table, "15659829", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 3, 1, fontNormal, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            table.addCell(cell);


            cell = insertCell(table, "Truck Number", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 1, 2, fontBold, backgroundLight);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);

            table.addCell(cell);

            /*Truck Number*/
//            cell = insertCell(table, invoiceDTO.getTruckNo(), Element.ALIGN_CENTER, Element.ALIGN_CENTER, 1, 1, fontNormal, null);
            cell = insertCell(table,"TN888H3906", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 1, 1, fontNormal, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            cell.setPaddingBottom(10f);
            table.addCell(cell);


            cell = insertCell(table, "Vendor Name", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 2, 2, fontBold, backgroundLight);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            table.addCell(cell);

            /*Vendor Name*/
            cell = insertCell(table, "MOTHERSON AUTOMOTIVE\n" +
                    "TECHNOLOGIES &", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 3, 2, fontNormal, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            table.addCell(cell);

            /*Truck Number*/
            Barcode128 barcodeEAN = new Barcode128();
            barcodeEAN.setCodeType(Barcode.CODE128);
//            barcodeEAN.setCode(invoiceDTO.getTruckNo());
            barcodeEAN.setCode("TN888H3906");
            barcodeEAN.setFont(null);
            barcodeEAN.setBarHeight(30f);
            barcodeEAN.setX(1f);
            PdfPCell imgBarCode = null;
            try {
                Image codeEANImage = barcodeEAN.createImageWithBarcode(pdfWriter.getDirectContent(), null, null);
                codeEANImage.scalePercent(60);
                codeEANImage.setAlignment(Image.ALIGN_CENTER);
                imgBarCode = new PdfPCell();
                imgBarCode.addElement(codeEANImage);
                imgBarCode.setVerticalAlignment(Element.ALIGN_TOP);
                imgBarCode.setRowspan(1);
                imgBarCode.setColspan(1);
                imgBarCode.setPaddingBottom(5f);
                imgBarCode.setBorder(Rectangle.RIGHT | Rectangle.BOTTOM | Rectangle.LEFT);
                table.addCell(imgBarCode);
            } catch (Exception e) {
                logger.error(e);
            }

            cell = insertCell(table, "DICV Plant", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 1, 1, fontBold, backgroundLight);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
            table.addCell(cell);

            /*DICV Plant*/
            cell = insertCell(table, "Truck", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 1, 1, fontNormal, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
            table.addCell(cell);

            cell = insertCell(table, "Pre-GVN Number", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 2, 1, fontBold, backgroundLight);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            table.addCell(cell);

            /*GVN Number*/
//            cell = insertCell(table, invoiceDTO.getPreGVNNo(), Element.ALIGN_CENTER, Element.ALIGN_CENTER, 2, 3, fontNormal, null);
            cell = insertCell(table, "79104", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 2, 1, fontNormal, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            cell.setPaddingBottom(15f);
            table.addCell(cell);

            /*Pre GVN Number*/
            barcodeEAN = new Barcode128();
            barcodeEAN.setCodeType(Barcode.CODE128);
//            barcodeEAN.setCode(invoiceDTO.getPreGVNNo());
            barcodeEAN.setCode("79104");
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
                imgBarCode.setColspan(3);
                imgBarCode.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.LEFT);
                table.addCell(imgBarCode);
            } catch (Exception e) {
                logger.error(e);
            }

            cell = insertCell(table, "S.No", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 2, 1, fontBold, backgroundLight);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            table.addCell(cell);

            cell = insertCell(table, "Invoice Number", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 1, 1, fontBold, backgroundLight);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            table.addCell(cell);

            cell = insertCell(table, "ASN Number", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 2, 1, fontBold, backgroundLight);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            table.addCell(cell);

            cell = insertCell(table, "Barcode", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 2, 1, fontBold, backgroundLight);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
            table.addCell(cell);

            /*SL no*/
            cell = insertCell(table, "1", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 2, 1, fontNormal, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            table.addCell(cell);

            /*Invoice No*/
//            cell = insertCell(table, invoiceDTO.getInvoiceNo(), Element.ALIGN_CENTER, Element.ALIGN_CENTER, 1, 1, fontNormal, null);
            cell = insertCell(table, "1821507553", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 1, 1, fontNormal, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            table.addCell(cell);

            /*ASN No*/
//            cell = insertCell(table, invoiceDTO.getAsnNo(), Element.ALIGN_CENTER, Element.ALIGN_CENTER, 2, 1, fontNormal, null);
            cell = insertCell(table, "1821507553", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 2, 1, fontNormal, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
            table.addCell(cell);

            /*ASN Barocde*/
            barcodeEAN = new Barcode128();
            barcodeEAN.setCodeType(Barcode.CODE128);
//            barcodeEAN.setCode(invoiceDTO.getAsnNo())
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
                imgBarCode.setColspan(2);
                imgBarCode.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.LEFT);
                table.addCell(imgBarCode);
            } catch (Exception e) {
                logger.error(e);
            }


            cell = insertCell(table, "Remarks", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 2, 1, fontBold, null);
            cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
            table.addCell(cell);

            /*Remarks*/
            cell = insertCell(table, "", Element.ALIGN_CENTER, Element.ALIGN_CENTER, 13, 4, fontNormal, null);
            cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT);


            table.addCell(cell);

            document.add(table);
            document.close();


        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Inside Catch Block");

        }
    }
}
