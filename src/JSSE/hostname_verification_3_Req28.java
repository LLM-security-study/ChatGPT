import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;

public class hostname_verification_3_Req28 {
    public static void main (String[] args) {
        String hostname = "example.com";

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(hostname, 443);

            // Enable https hostname verification
            SSLParameters sslParams = new SSLParameters();
            sslParams.setEndpointIdentificationAlgorithm("HTTPS");
            socket.setSSLParameters(sslParams);

            // Now as the setEndpointIdentificationAlgorithm method has been called 
            // with the "HTTPS" parameter, hostname verification will be automatically
            // performed once the connection has been established.

            // Start handshaking
            socket.startHandshake();

            // Always remember to close the socket when done
            socket.close();
            
            System.out.println("Hostname is valid!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}