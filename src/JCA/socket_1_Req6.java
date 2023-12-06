import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req6 {
    private static final String SERVER = "localhost";
    private static final int PORT = 1234;

    public static void main(String[] args) {

        try {
            // Get SSLSocketFactory
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            // Create SSLSocket
            SSLSocket socket = (SSLSocket) factory.createSocket(SERVER, PORT);

            // Start handshake
            socket.startHandshake();

            // Get writer to send data
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("Hello Server!");

            // Get reader to read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Response from server: " + reader.readLine());

            // Cleaning up the streams and the socket
            writer.close();
            reader.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Error occurred during communication: " + e.getMessage());
        }
    }
}