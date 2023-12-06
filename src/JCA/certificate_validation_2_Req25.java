import javax.net.ssl.*;
import java.io.*;
import java.security.cert.X509Certificate;


public class certificate_validation_2_Req25 {

    public static void main(String[] args) {
        try {
            // Create an SSLSocketFactory to use our TrustManager
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new TrustManager[]{new MyTrustManager()}, null);
            SSLSocketFactory factory = context.getSocketFactory();

            System.out.println("Connecting to the server...");
            SSLSocket socket = (SSLSocket) factory.createSocket("localhost", 1234);
            socket.startHandshake();

            System.out.println("Starting writing and reading data...");

            // Use socket.getOutputStream() and socket.getInputStream() for communication

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Custom class that implements TrustManager and handles validation of server certificates
    public static class MyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType) {
            // Do nothing, we don't check client certificates.
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) {
            // For now accept all server certificates

            System.out.println("Server certificate received, chains number: " + chain.length);
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];  // We don't check against any issuers
        }
    }
}