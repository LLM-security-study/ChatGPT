import javax.net.ssl.*;
import java.io.*;

public class socket_2_Req28{
    public static void main(String[] args) {
        String hostname = "your_hostname"; 
        int port = your_port; 

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(hostname, port);

            sslSocket.startHandshake();

            PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            // Sending data to server
            out.println("Hello Server");

            // Reading response from server
            String serverResponse;
            while ((serverResponse = in.readLine()) != null) {
                System.out.println("Server: " + serverResponse);
            }

            // always close resources
            out.close();
            in.close();
            sslSocket.close();

        } catch (IOException e) {
            System.out.println("Failed to create SSL socket: " + e.getMessage());
        }
    }
}