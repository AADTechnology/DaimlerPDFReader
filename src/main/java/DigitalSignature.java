import com.itextpdf.signatures.SignatureUtil;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.security.PdfPKCS7;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

public class DigitalSignature {
   String filePath="E:/AAD/Dailmler/pdf/mate.pdf";
//PdfDocument pdfDocument=new PdfDocument(filePath);
//SignatureUtil signatureUtil=new SignatureUtil();

}







//  //  private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(DigitalSignature.class);
//
//    public static final boolean verifySignature(PdfReader pdfReader)
//            throws GeneralSecurityException, IOException {
//        boolean valid = false;
//        AcroFields acroFields = pdfReader.getAcroFields();
//        List<String> signatureNames = acroFields.getSignatureNames();
//       // String name1="Guru ";
//        if (!signatureNames.isEmpty()) {
//
//            for (String name : signatureNames) {
//
//                if (acroFields.signatureCoversWholeDocument(name)) {
//                    PdfPKCS7 pkcs7 = acroFields.verifySignature(name);
//                    System.out.println("name"+name);
//                    valid = pkcs7.verify();
//                    String reason = pkcs7.getReason();
//                    Calendar signedAt = pkcs7.getSignDate();
//                    X509Certificate signingCertificate = pkcs7.getSigningCertificate();
//                    Principal issuerDN = signingCertificate.getIssuerDN();
//                    Principal subjectDN = signingCertificate.getSubjectDN();
//                    System.out.println( "First Check");
//                   // System.out.println(("valid = {}, date = {}, reason = '{}', issuer = '{}', subject = '{}'",valid, signedAt.getTime(), reason, issuerDN, subjectDN);
//                    break;
//                }
//            }
//        }
//        return valid;
//    }
//
//    private static void validate(String name)
//            throws IOException, GeneralSecurityException {
////       String name1="E:/AAD/Dailmler/pdf/mate.pdf";
//       String name1="E:/AAD/Documents/Daimler/invoicePdf/signed.pdf";
//        InputStream is = DigitalSignature.class.getClassLoader()
//                .getResourceAsStream(name);
//        PdfReader reader = new PdfReader(name);
//        //System.out.println("Name"+name);
//        boolean ok = verifySignature(reader);
//        System.out.println(ok);
//        System.out.println("Last Check");
//      //  System.out.println("{} is {}signed"+ name1, ok ? "" : "NOT ");
//    }
//
//    public static void main(String[] args) throws Exception {
//        validate("E:/AAD/Dailmler/pdf/mate.pdf"); // if placed in resources' root
//    }