import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_1_Req20 {
    public static void main(String[] args) {
        String hostname = "www.example.com";
        try {
            URL url = new URL("https://www.example.com");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            SSLSession sslSession = connection.getSSLSession();
            if (sslSession != null) {
                java.security.cert.Certificate[] certificates = sslSession.getPeerCertificates();
                
                if (certificates.length > 0) {
                    javax.security.cert.X509Certificate x509Certificate = javax.security.cert.X509Certificate.getInstance(certificates[0].getEncoded());
                    String dn = x509Certificate.getSubjectDN().getName();

                    int cnIndex = dn.indexOf("CN=");
                    if (cnIndex != -1) {
                        String serverName = dn.substring(cnIndex + 3);
                        System.out.println(sslSession.isValid() && serverName.equals(hostname) ? "Host name is verified." : "Host name isn't matching.");
                    } else {
                        System.out.println("Can't find CN record in the server certificate subject DN.");
                    }
                }
            }
        } catch (SSLPeerUnverifiedException e) {
            System.out.println("SSL peer verification failed: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while connecting to the SSL server: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}