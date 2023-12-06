import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req26 {
    
    private static final String SERVER_IP = "server_ip";
    private static final int SERVER_PORT = 7777;

    public static void main(String[] args) {
        try {
            // Get SSLSocketFactory
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            // Create SSLSocket
            SSLSocket socket = (SSLSocket) factory.createSocket(SERVER_IP, SERVER_PORT);

            // Initialize the socket
            socket.startHandshake();

            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send a request to the server
            String request = "Hello Server";
            out.println(request);
            out.flush();

            // Read response from the server
            String response = in.readLine();
            System.out.println("Server response: " + response);

            // Close the IO resources
            out.close();
            in.close();

            // Close the socket
            socket.close();

        } catch (IOException e) {
            System.err.println("I/O error in SSL connection: "+ e);
        }
    }
}