import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_2_Req19 {
    public static void main(String[] args) {
        try {
            // Specify the host and port here
            String host = "localhost";
            int port = 1234;

            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
            
            // Enable all the suites
            String[] supported = socket.getSupportedCipherSuites();
            socket.setEnabledCipherSuites(supported);

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);
            
            // A simple hello message to the server
            out.println("Hello, server!");

            // Closing the streams and the socket
            out.close();
            outputStream.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Failed to establish SSL socket connection");
            e.printStackTrace();
        }
    }
}