import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req20 {
    public static void main(String[] args) {

        int port = 1234; // Port to connect to
        String host = "localhost"; // Host name or IP address

        try {
            // Get SSLSocketFactory
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            // Create an SSLSocket
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(host, port);

            // Writer for sending request to the server
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream())));

            // Read response from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            String message = "Hello Secure World!";
            out.println(message);
            out.flush();
            System.out.println("Sent to server: " + message);

            String response = in.readLine();
            System.out.println("Received from server: " + response);

            // Close connections
            in.close();
            out.close();
            sslSocket.close();

        } catch (IOException e) {
            System.out.println("Error occurred : " + e.getMessage());
            e.printStackTrace();
        }
    }
}