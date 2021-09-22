

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfDocument;
//import com.itextpdf.signature.Signa
import com.itextpdf.text.pdf.PdfReader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

public class PDFReader {
    public static void main(String args[]) throws IOException {
String invoiceNo="1821506559";
String gstin="33AAACM0405A1ZK";
String qty="10";
String partNo="4004620023";
String poNO="1073100223";
String uom="J7087089900";
String IncoTerm="";
String IncoTermText="";
String sac="33";


PdfDocument pdfDoc=new PdfDocument();
            PdfReader reader= new PdfReader("E:/AAD/Dailmler/pdf/mate.pdf");
            AcroFields acroFields = reader.getAcroFields();
          //  List<String> signatureNames = acroFields.getSignatureNames();

//        File file= new File("E:/AAD/Dailmler/pdf/mate.pdf");
        File file= new File("E:/AAD/Documents/Daimler/invoicePdf/NIPPON_AUDIOTRONIX.pdf");
        PDDocument doc=PDDocument.load(file);
        System.out.println("Test git");
        PDFTextStripper pdfTextStripper=new PDFTextStripper();
        String text= pdfTextStripper.getText(doc);
            System.out.println(text);
        System.out.println("Invoice No "+ text.contains(invoiceNo));
        System.out.println("GSTIN NO "+ text.contains(gstin));
        System.out.println("SAS CODE "+ text.contains(sac));
        System.out.println("Inco Term Text "+ text.contains(IncoTermText));
        System.out.println("Inco Term "+ text.contains(IncoTerm));
        System.out.println("Qty "+ text.contains(qty));
        System.out.println("PO No "+ text.contains(poNO));
        System.out.println("Part No "+ text.contains(partNo));
        System.out.println("UOM "+ text.contains(uom));


        doc.close();
    }
}





////        File file = new File("E://AAD//Dailmler//pdf//asahi.pdf");
//        File file = new File("E:/AAD/Dailmler/pdf/test.pdf");
////    E:\AAD\Dailmler\pdf\asahi.pdf
//        PDDocument document=PDDocument.load(file);
//        PDFTextStripper pdfTextStripper=new PDFTextStripper();
//        String text= pdfTextStripper.getText(document);
//        System.out.println("hello");
//        System.out.println(text);
//        document.close();
//try (PDDocument pdfDocument = PDDocument.load(new File("E:/AAD/Dailmler/pdf/test.pdf"))) {
//        PDDocument doc = new PDDocument();
//
//        PDPage blankPage=new PDPage();
//        doc.addPage(blankPage);
//        doc.save("E:/AAD/Dailmler/pdf/Create.pdf");
//        System.out.println("PDF Created");
//        doc.close();