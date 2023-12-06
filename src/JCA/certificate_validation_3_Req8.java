import javax.net.ssl.*;
import java.io.IOException;
import java.net.InetAddress;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;

public class certificate_validation_3_Req8 {
    public static void main(String[] args) {
        try {
            // Create TrustManager
            TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        // Trusted
                    }
                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                        if (certs == null || certs.length == 0) {
                            throw new IllegalArgumentException("Certificate chain is null or empty.");
                        }
                        if (authType == null || authType.length() == 0) {
                            throw new IllegalArgumentException("Authentication type is null or empty.");
                        }

                        for (X509Certificate cert : certs) {
                            cert.checkValidity(); 
                            // You can add more validation logic here
                            
                        }
                    }
                }
            };

            // Install TrustManager to SSLContext
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create SSLSocket from the SSLContext
            SSLSocketFactory ssf = sc.getSocketFactory();
            SSLSocket socket = (SSLSocket) ssf.createSocket(InetAddress.getByName("your-server.com"), 443);

            // Start Handshake
            socket.startHandshake();

            // Print the result
            System.out.println("Validation successful!");

            // Close the connection
            socket.close();

        } catch (NoSuchAlgorithmException | KeyManagementException | IOException | CertificateException e) {
            e.printStackTrace();
        }
    }
}