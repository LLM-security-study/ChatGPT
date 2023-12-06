import javax.net.ssl.*;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class certificate_validation_1_Req18 {
    public static void main(String[] args) {
        try {
            // Specify the keystore details
            System.setProperty("javax.net.ssl.keyStore", "myKeyStore.jks");
            System.setProperty("javax.net.ssl.keyStorePassword", "password");

            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }
            }};
  
            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Load the certificate file
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            FileInputStream fis = new FileInputStream("serverCertificate.cert");

            X509Certificate serverCert = (X509Certificate) cf.generateCertificate(fis);
            fis.close();

            // Connect to the server
            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("localhost", 443);

            // Get the server's certificate chain
            SSLSession session = sslsocket.getSession();
            java.security.cert.Certificate[] serverCerts = session.getPeerCertificates();

            // Validate the server certificate
            serverCert.checkValidity();

            System.out.println("Server certificate is valid");

        } catch (Exception e) {
            System.out.println("Server certificate is invalid");
            e.printStackTrace();
        }
    }
}