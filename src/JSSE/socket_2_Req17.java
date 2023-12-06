import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_2_Req17 {
    public static void main(String[] args) {
        String host = "www.yourserver.com";
        int port = 1234; // example port
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(host, port);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            // Send a message to the server
            String messageToServer = "Hello, server!";
            writer.write(messageToServer);
            writer.newLine();
            writer.flush();
            
            // Read the server's response
            String response = reader.readLine();
            System.out.println("Server response: " + response);

            // Closing all resources
            writer.close();
            reader.close();
            sslSocket.close();

        } catch (IOException e) {
            System.out.println("Unable to establish SSL socket:");
            e.printStackTrace();
        }
    }
}