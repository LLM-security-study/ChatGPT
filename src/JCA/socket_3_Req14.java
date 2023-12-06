import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req14 {

    private static final String HOST = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try {
            // Get SSL socket factory
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            // Create SSLSocket
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(HOST, PORT);

            // Initialize the streams for communication with the server
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream())));
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            // Send a message to the server
            out.println("Hello Server");
            out.flush();

            // Read the response from the server
            String response = in.readLine();
            System.out.println("Server response: " + response);

            // Close the streams and the socket
            out.close();
            in.close();
            sslSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}