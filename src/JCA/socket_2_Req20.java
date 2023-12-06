import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_2_Req20 {
    public static void main(String[] args) {
        try {
            // Create a SSLSocketFactory
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            
            // Use the factory to create an SSLSocket
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("localhost", 8000);

            // Create input & output streams
            DataOutputStream outputStream = new DataOutputStream(sslSocket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(sslSocket.getInputStream());
            
            // Send to and receive data from the server
            outputStream.writeUTF("Hello from the client!");
            System.out.println(inputStream.readUTF());

            // Close the socket
            sslSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}