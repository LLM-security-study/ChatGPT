import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class socket_2_Req22 {
    public static void main(String[] args) {
        String host = "YourHost";
        int port = 443; // replace with your port 
        try {
            
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(host, port);
            
            PrintWriter output = new PrintWriter(sslSocket.getOutputStream());
            BufferedReader input = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            
            // Send some data
            output.println("Hello World");
            output.flush();
            
            // Receive data
            String response = input.readLine();
            System.out.println("Received: " + response);
            
            // Close connections
            output.close();
            input.close();
            sslSocket.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}