import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_2_Req26 {
    public static void main(String[] args) {
        String remoteHost = "RemoteHostName";   // Replace with your remote host / server
        int port = 443; // Use your remote server port number. Here 443 is used as an example
        
        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(remoteHost, port);
            
            // For writing to the socket
            PrintWriter out = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            
            // For reading from the socket
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            
            // Send a message to the server
            out.println("Hello Server");
            out.flush();
            
            // Read the server response 
            String line = in.readLine();
            System.out.println("Server Response: " + line);
            
            // Close input, output streams and the socket
            out.close();
            in.close();
            sslSocket.close();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}