import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class certificate_validation_3_Req20 {
    
    public static void main(String[] args) {
        try {
            // Create an SSLSocketFactory
            SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
            
            // Create an SSLSocket
            SSLSocket socket = (SSLSocket)factory.createSocket("www.google.com", 443);
            
            // Start the handshake process with the server
            socket.startHandshake();
            
            // Create a BufferedReader to read the server's response
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Print the server's response
            String response;
            while ((response = reader.readLine()) != null) {
                System.out.println(response);
            }
            
            // Close the reader and socket
            reader.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}