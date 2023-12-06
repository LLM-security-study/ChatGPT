import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class socket_3_Req25 {
    private static final String HOST = "your.remote.server"; // replace with your server address
    private static final int PORT = 1234; // replace with your server port

    public static void main(String[] args) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(HOST, PORT);

            // Enable all the suites
            String[] supported = socket.getSupportedCipherSuites();
            socket.setEnabledCipherSuites(supported);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Write to the socket
            out.println("Hello Server!");

            // Read from the socket
            String response = in.readLine();
            System.out.println("Server Response: " + response);

            // Close the socket and its streams
            out.close();
            in.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}