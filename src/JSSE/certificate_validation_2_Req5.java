import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.net.MalformedURLException;

public class certificate_validation_2_Req5 {
    
    public static void main(String[] args) {
        validateCertificate("https://www.google.com");
    }

    private static void validateCertificate(String aURLStr) {
        try {
            URL url = new URL(aURLStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.connect();
            Certificate[] certs = conn.getServerCertificates();

            for (Certificate cert : certs) {
                if(cert instanceof X509Certificate) {
                    try {
                        MessageDigest md = MessageDigest.getInstance("SHA");
                        md.update(((X509Certificate) cert).getEncoded());

                        System.out.println("Subject: " + ((X509Certificate) cert).getSubjectDN());
                        System.out.println("Issuer: " + ((X509Certificate) cert).getIssuerDN());
                        System.out.println("SHA Digest: " + toHexString(md.digest()));
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 3);
        for (byte b : bytes) {
            sb.append(Integer.toHexString(b & 0xff));
        }
        return sb.toString();
    }
}