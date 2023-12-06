import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.net.InetAddress;
import java.net.URL;
import java.util.Arrays;

public class hostname_verification_1_Req29 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.example.com");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            // Obtain the SSLSocketFactory object from SSLContext class
            SSLContext context = SSLContext.getDefault();
            SSLSocketFactory factory = context.getSocketFactory();
            
            // Create a socket
            InetAddress host = InetAddress.getByName(url.getHost());
            SSLSocket socket = (SSLSocket) factory.createSocket(host, 443);

            // Enable hostname verification
            SSLParameters params = socket.getSSLParameters();
            params.setEndpointIdentificationAlgorithm("HTTPS");
            socket.setSSLParameters(params);

            // Start the handshake and verify
            socket.startHandshake();

            System.out.println("Hostname verification successful");

            // Close connections
            socket.close();
            connection.disconnect();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}