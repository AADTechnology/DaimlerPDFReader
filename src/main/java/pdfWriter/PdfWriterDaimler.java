package pdfWriter;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfWriterDaimler {
    private static final Logger logger = Logger.getLogger(PdfWriter.class);

    public static void main(String args[]) {
        createPdfOne();
        System.out.println("Created PDF");
    }

    public static PdfPCell insertCell(PdfPTable table, String text, int horAlign, int verAligh, int colSpan, int rowSpan, Font font, BaseColor backgroundColor){
        PdfPCell cell=new PdfPCell(new Phrase(text.trim(),font));
        cell.setPaddingTop(0);
        cell.setHorizontalAlignment(horAlign);
        if (verAligh > 0) {
            cell.setVerticalAlignment(verAligh);
        }
        cell.setRowspan(rowSpan);

        cell.setColspan(colSpan);
        if (backgroundColor != null)
            cell.setBackgroundColor(backgroundColor);

        // in case there is no text and you wan to create an empty row
        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }
        // add the call to the table
        // table.addCell(cell);
        return  cell;
    }

    public static void createPdfOne() {

        {
            try {
                Document document = new Document(PageSize.A4, 5f, 30f, 20f, 5f);
                PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("E:/AAD/Documents/Daimler/CreatePdf/first.pdf"));
                document.open();
                Font font9 = new Font(Font.FontFamily.TIMES_ROMAN, 7f, Font.NORMAL, BaseColor.BLACK);
                Font font10 = new Font(Font.FontFamily.TIMES_ROMAN, 8f, Font.BOLD, BaseColor.BLACK);
                Font font15 = new Font(Font.FontFamily.TIMES_ROMAN, 13f, Font.BOLD, BaseColor.BLACK);
                Font font20 = new Font(Font.FontFamily.TIMES_ROMAN, 18f, Font.BOLD, BaseColor.BLACK);
                Font font40 = new Font(Font.FontFamily.TIMES_ROMAN, 35f, Font.BOLD, BaseColor.BLACK);
                Font font30 = new Font(Font.FontFamily.TIMES_ROMAN, 25f, Font.BOLD, BaseColor.BLACK);
                Font font20NoBold = new Font(Font.FontFamily.TIMES_ROMAN, 18f, Font.NORMAL, BaseColor.BLACK);
                System.out.println("Testing of Creation");


                float[] columnWidths = {13f, 13f, 13f, 10f, 5f, 13f, 5f, 12f, 8f, 13f};
                PdfPTable table = new PdfPTable(columnWidths);
                table.setWidthPercentage(95f);
                PdfPCell cell = insertCell(table, "PART NO.", Element.ALIGN_LEFT, Element.ALIGN_CENTER, 1, 2, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
                table.addCell(cell);
                cell = insertCell(table, "A.400.262.09.62", Element.ALIGN_LEFT, Element.ALIGN_CENTER, 3, 2, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
                table.addCell(cell);

                Barcode128 barcodeEAN = new Barcode128();
                barcodeEAN.setCodeType(Barcode.CODE128);
                barcodeEAN.setCode("A.400.262.09.62");
                barcodeEAN.setFont(null);
                barcodeEAN.setBarHeight(18f);
                barcodeEAN.setX(1f);
                PdfPCell imgBarCode = null;
                try {
                    Image codeEANImage = barcodeEAN.createImageWithBarcode(pdfWriter.getDirectContent(), null, null);
                    codeEANImage.scalePercent(100);
                    codeEANImage.setAlignment(Image.ALIGN_LEFT);
                    imgBarCode = new PdfPCell();
                    imgBarCode.setPadding(5f);
                    imgBarCode.addElement(codeEANImage);
                    imgBarCode.setVerticalAlignment(Element.ALIGN_BOTTOM);
                    // imgBarCode.setBackgroundColor(backgroundLight);
                    imgBarCode.setRowspan(2);
                    imgBarCode.setColspan(5);

                    imgBarCode.setBorder(Rectangle.RIGHT|Rectangle.TOP);
                    table.addCell(imgBarCode);// Trolley BarCode
                } catch (Exception e) {
                    logger.error(e);
                }


                cell = insertCell(table, "PLANT ", Element.ALIGN_LEFT, Element.ALIGN_TOP, 1, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
                table.addCell(cell);

                cell = insertCell(table, "TRUCK", Element.ALIGN_CENTER, Element.ALIGN_TOP, 1, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                table.addCell(cell);




                cell = insertCell(table, "PART DESCRIPTION", Element.ALIGN_LEFT, Element.ALIGN_TOP, 5, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
                table.addCell(cell);

                cell = insertCell(table, "ZGS NO.", Element.ALIGN_LEFT, Element.ALIGN_TOP, 1, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
                table.addCell(cell);

                cell = insertCell(table, "KEM NO.", Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
                table.addCell(cell);

                cell = insertCell(table, "INVOICE QTY:", Element.ALIGN_LEFT, Element.ALIGN_TOP, 1, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
                table.addCell(cell);

                cell = insertCell(table, "THRUST WASHER", Element.ALIGN_CENTER, Element.ALIGN_TOP, 5, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                table.addCell(cell);

                cell = insertCell(table, "", Element.ALIGN_CENTER, Element.ALIGN_TOP, 1, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                table.addCell(cell);

                cell = insertCell(table, "", Element.ALIGN_CENTER, Element.ALIGN_TOP, 3, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT );
                table.addCell(cell);

                cell = insertCell(table, "300", Element.ALIGN_CENTER, Element.ALIGN_TOP, 3, 1, font10, null);
                cell.setBorder(Rectangle.LEFT  | Rectangle.RIGHT);
                table.addCell(cell);

                cell = insertCell(table, "ASN NO:"+"180412173", Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, font10, null);
                cell.setBorder(Rectangle.LEFT  | Rectangle.TOP);
                table.addCell(cell);

                cell = insertCell(table, "VENDOR CODE:", Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 1, font10, null);
                cell.setBorder(Rectangle.LEFT  | Rectangle.TOP);
                table.addCell(cell);

                cell = insertCell(table, "PACK QTY.", Element.ALIGN_LEFT, Element.ALIGN_TOP, 1, 1, font10, null);
                cell.setBorder(Rectangle.LEFT  | Rectangle.TOP);
                table.addCell(cell);

                cell = insertCell(table, "REQUIRED PACKS", Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 1, font10, null);
                cell.setBorder(Rectangle.LEFT  | Rectangle.TOP);
                table.addCell(cell);

                cell = insertCell(table, "ACTUAL ", Element.ALIGN_LEFT, Element.ALIGN_TOP, 1, 1, font10, null);
                cell.setBorder(Rectangle.LEFT  | Rectangle.RIGHT | Rectangle.TOP);
                table.addCell(cell);

                Barcode128 barcodeEANASN = new Barcode128();
                barcodeEAN.setCodeType(Barcode.CODE128);
                barcodeEAN.setCode("180412173");
                barcodeEAN.setFont(null);
                barcodeEAN.setBarHeight(20f);
                barcodeEAN.setX(1f);
                PdfPCell imgAsNBarCode = null;
                try {
                    Image codeEANImage = barcodeEAN.createImageWithBarcode(pdfWriter.getDirectContent(), null, null);
                    codeEANImage.scalePercent(80);
                    codeEANImage.setAlignment(Image.ALIGN_CENTER);
                    imgBarCode = new PdfPCell();
                    // imgBarCode.setPadding(5f);
                    imgBarCode.addElement(codeEANImage);
                    imgBarCode.setVerticalAlignment(Element.ALIGN_BOTTOM);
                    // imgBarCode.setBackgroundColor(backgroundLight);
                    imgBarCode.setRowspan(1);
                    imgBarCode.setColspan(2);

                    imgBarCode.setBorder(Rectangle.BOTTOM|Rectangle.LEFT);
                    table.addCell(imgBarCode);// Trolley BarCode
                } catch (Exception e) {
                    logger.error(e);
                }

                cell = insertCell(table, "15660561", Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 1, font10, null);
                cell.setBorder(Rectangle.LEFT  | Rectangle.BOTTOM);
                table.addCell(cell);
/*Packed Qty*/
                cell = insertCell(table, "10", Element.ALIGN_CENTER, Element.ALIGN_TOP, 1, 1, font10, null);
                cell.setBorder(Rectangle.LEFT  | Rectangle.BOTTOM);
                table.addCell(cell);

                cell = insertCell(table, "30", Element.ALIGN_CENTER, Element.ALIGN_TOP, 3, 1, font10, null);
                cell.setBorder(Rectangle.LEFT  | Rectangle.BOTTOM);
                table.addCell(cell);

                cell = insertCell(table, "30", Element.ALIGN_CENTER, Element.ALIGN_TOP, 1, 1, font10, null);
                cell.setBorder(Rectangle.LEFT  | Rectangle.RIGHT | Rectangle.BOTTOM);
                table.addCell(cell);

                cell = insertCell(table, "RTP CODE", Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                table.addCell(cell);

                cell = insertCell(table, "SA/PO NO.", Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, font10, null);
                cell.setBorder(Rectangle.LEFT );
                table.addCell(cell);

                Barcode128 barcodeEANSa= new Barcode128();
                barcodeEAN.setCodeType(Barcode.CODE128);
                barcodeEAN.setCode("5500000762");
                barcodeEAN.setFont(null);
                barcodeEAN.setBarHeight(20f);
                barcodeEAN.setX(1f);
                PdfPCell imgSABarCode = null;
                try {
                    Image codeEANImage = barcodeEAN.createImageWithBarcode(pdfWriter.getDirectContent(), null, null);
                    codeEANImage.scalePercent(80);
                    codeEANImage.setAlignment(Image.ALIGN_CENTER);
                    imgBarCode = new PdfPCell();
                    // imgBarCode.setPadding(5f);
                    imgBarCode.addElement(codeEANImage);
                    imgBarCode.setVerticalAlignment(Element.ALIGN_BOTTOM);
                    // imgBarCode.setBackgroundColor(backgroundLight);
                    imgBarCode.setRowspan(2);
                    imgBarCode.setColspan(3);

                    imgBarCode.setBorder(Rectangle.BOTTOM);
                    table.addCell(imgBarCode);// Trolley BarCode
                } catch (Exception e) {
                    logger.error(e);
                }

                cell = insertCell(table, "LABEL NO.", Element.ALIGN_LEFT, Element.ALIGN_TOP,1 , 1, font10, null);
                cell.setBorder(Rectangle.LEFT );
                table.addCell(cell);

                Barcode128 barcodeEANlabel= new Barcode128();
                barcodeEAN.setCodeType(Barcode.CODE128);
                barcodeEAN.setCode("1/30");
                barcodeEAN.setFont(null);
                barcodeEAN.setBarHeight(20f);
                barcodeEAN.setX(1f);
                PdfPCell imgLabelNoBarCode = null;
                try {
                    Image codeEANImage = barcodeEAN.createImageWithBarcode(pdfWriter.getDirectContent(), null, null);
                    codeEANImage.scalePercent(70);
                    codeEANImage.setAlignment(Image.ALIGN_CENTER);
                    imgBarCode = new PdfPCell();
                    // imgBarCode.setPadding(5f);
                    imgBarCode.addElement(codeEANImage);
                    imgBarCode.setVerticalAlignment(Element.ALIGN_BOTTOM);
                    // imgBarCode.setBackgroundColor(backgroundLight);
                    imgBarCode.setRowspan(2);
                    imgBarCode.setColspan(2);

                    imgBarCode.setBorder(Rectangle.BOTTOM|Rectangle.RIGHT);
                    table.addCell(imgBarCode);// Trolley BarCode
                } catch (Exception e) {
                    logger.error(e);
                }


                cell = insertCell(table, "S.C161.208V30.8", Element.ALIGN_CENTER, Element.ALIGN_TOP, 2, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
                table.addCell(cell);

                cell = insertCell(table, "5500000762", Element.ALIGN_CENTER, Element.ALIGN_TOP, 2, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
                table.addCell(cell);

                cell = insertCell(table, "1/30", Element.ALIGN_LEFT, Element.ALIGN_TOP, 1, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
                table.addCell(cell);

                cell = insertCell(table, "DELIVERY DATE", Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                table.addCell(cell);

                cell = insertCell(table, "STORAGE LOCATION", Element.ALIGN_LEFT, Element.ALIGN_TOP, 2, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                table.addCell(cell);

                cell = insertCell(table, "INVOICE NO.", Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 1, font10, null);
                cell.setBorder(Rectangle.LEFT );
                table.addCell(cell);

                Barcode128 barcodeEANInvNo = new Barcode128();
                barcodeEAN.setCodeType(Barcode.CODE128);
                barcodeEAN.setCode("12345");
                barcodeEAN.setFont(null);
                barcodeEAN.setBarHeight(20f);
                barcodeEAN.setX(1f);
                PdfPCell imgInvNoBarCode = null;
                try {
                    Image codeEANImage = barcodeEAN.createImageWithBarcode(pdfWriter.getDirectContent(), null, null);
                    codeEANImage.scalePercent(70);
                    codeEANImage.setAlignment(Image.ALIGN_CENTER);
                    imgBarCode = new PdfPCell();
                    // imgBarCode.setPadding(5f);
                    imgBarCode.addElement(codeEANImage);
                    imgBarCode.setVerticalAlignment(Element.ALIGN_BOTTOM);
                    // imgBarCode.setBackgroundColor(backgroundLight);
                    imgBarCode.setRowspan(2);
                    imgBarCode.setColspan(3);

                    imgBarCode.setBorder(Rectangle.BOTTOM|Rectangle.RIGHT);
                    table.addCell(imgBarCode);// Trolley BarCode
                } catch (Exception e) {
                    logger.error(e);
                }

                cell = insertCell(table, "12.08.2021", Element.ALIGN_CENTER, Element.ALIGN_TOP, 2, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
                table.addCell(cell);

                cell = insertCell(table, "MAC01D2-D2", Element.ALIGN_CENTER, Element.ALIGN_TOP, 2, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
                table.addCell(cell);

                cell = insertCell(table, "12345.", Element.ALIGN_CENTER, Element.ALIGN_TOP, 3, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
                table.addCell(cell);


                cell = insertCell(table, "SHIP FROM", Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                table.addCell(cell);

                cell = insertCell(table, "SHIP TO", Element.ALIGN_LEFT, Element.ALIGN_TOP, 5, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                table.addCell(cell);

                cell = insertCell(table, "Supplier Part Number", Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 1, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                table.addCell(cell);

                cell = insertCell(table, " M.D.INDUSTRIES\n" +
                        "H-28 MIDC AMBAD,,NASIK-422010, India", Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 3, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
                table.addCell(cell);

                cell = insertCell(table, "Daimler India Com.Vehicles Pvt.Ltd.\n" +
                        " Oragadam,Sriperumbudur - 602105, India", Element.ALIGN_LEFT, Element.ALIGN_TOP, 5, 3, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
                table.addCell(cell);

                cell = insertCell(table, "", Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 3, font10, null);
                cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM |Rectangle.RIGHT);
                cell.setPadding(10f);
                table.addCell(cell);







                document.add(table);
                document.close();


            } catch (FileNotFoundException | DocumentException e) {
                e.printStackTrace();
                System.out.println("Inside Catch Block");
            }
        }

    }
}


