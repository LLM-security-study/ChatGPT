import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req18{
    public static void main(String[] args) {
        String host = "yourserver.com";
        int port = 9999;  //appropriate port number
        try {
            // Get default SSL Socket Factory
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            // Create an SSL socket and start handshake
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(host, port);
            sslSocket.startHandshake();

            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream())));
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            // Send a message to the server
            out.println("Hello Server");
            out.flush();

            // Read the response from server
            String response = in.readLine();
            System.out.println("Server Response: " + response);

            // Shutdown and close the SSL socket
            out.close();
            in.close();
            sslSocket.close();

        } catch (IOException e) {
            System.out.println("Error occurred " + e.getMessage());
            e.printStackTrace();
        }
    }
}