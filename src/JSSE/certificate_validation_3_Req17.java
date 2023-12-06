import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class certificate_validation_3_Req17 {

    public static void main(String[] args) throws Exception {
        validateCert("yourHost", 443);
    }
    
    public static void validateCert(String host, int port) throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
        TrustManager[] trustManagers = new TrustManager[] {
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    // Throw an exception if the client certificate is invalid.
                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    // Here, you can validate the server's certificate.
                    // Throw an exception if it's invalid.
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    // Based on your requirement, you may return a list of trusted 
                    // certificate issuers. 
                    return null;
                }
            }
        };

        context.init(null, trustManagers, null);

        SSLSocketFactory factory = context.getSocketFactory();
        SSLSocket socket = (SSLSocket) factory.createSocket(host, port);

        // After this line executes, an SSL handshake is performed.
        // If the certificate is invalid, an exception will be thrown
        socket.startHandshake();

        System.out.println("Successfully validated the certificate for: " + host);
    }
}