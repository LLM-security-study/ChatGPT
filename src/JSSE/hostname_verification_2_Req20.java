import java.net.*;
import javax.net.ssl.*;

public class hostname_verification_2_Req20 {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: java hostname_verification_2_Req20 hostname");
            System.exit(1);
        }

        String hostname = args[0];
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) factory.createSocket(hostname, 443);
        
        // Enable hostname verification
        SSLParameters sslParams = new SSLParameters();
        sslParams.setEndpointIdentificationAlgorithm("HTTPS");
        socket.setSSLParameters(sslParams);

        // Now, verify the hostname
        try {
            // This will throw an exception if the certificate does not match hostname
            socket.startHandshake();
            System.out.println("Successfully verified hostname");
        } catch (SSLException exc) {
            System.err.println("Hostname verification failed");
        }
        // Don't forget to close your socket
        socket.close();
    }
}