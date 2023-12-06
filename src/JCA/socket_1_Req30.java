import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req30 {
    public static void main(String[] args) {
        String serverIP = "localhost";
        int portNum = 1234;

        try {
            // Create SSLSocketFactory
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            // Create SSLSocket
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(serverIP, portNum);

            // Initialize streams for communication
            DataOutputStream out = new DataOutputStream(sslSocket.getOutputStream());
            DataInputStream in = new DataInputStream(sslSocket.getInputStream());

            // Read/write data to/from server

            //...

            // Close connections
            out.close();
            in.close();
            sslSocket.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}