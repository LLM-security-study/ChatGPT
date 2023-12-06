import java.io.*;
import javax.net.ssl.*;

public class socket_3_Req18 {
    public static void main(String[] args) {
        String host = "example.com";
        int port = 1234;

        try {
            // Obtain a SSLSocketFactory object
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            // Creates an unconnected socket
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(host, port);

            // Initialize the IO streams for further communication
            PrintWriter output = new PrintWriter(sslSocket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            // Send a request to the server
            output.println("Client Request");

            // Read the server response
            String response = input.readLine();
            System.out.println("Server Response: " + response);

            // Closing the streams and the socket
            output.close();
            input.close();
            sslSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}