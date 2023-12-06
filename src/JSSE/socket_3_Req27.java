import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class socket_3_Req27 {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 443;

        try {
            // Create SSLSocketFactory
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            // Create SSLSocket
            SSLSocket socket = (SSLSocket) factory.createSocket(hostname, port);

            // Enable all the suites
            socket.setEnabledCipherSuites(socket.getSupportedCipherSuites());

            System.out.println("\nSSL Socket connection established to " + hostname + " on port " + port);

            // Create writer for the SSL socket
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Create reader for the SSL socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Perform communication with server

            // Close the SSL socket
            socket.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}