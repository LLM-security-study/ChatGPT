import java.io.*;
import javax.net.ssl.*;

public class socket_2_Req3 {
    public static void main(String[] args) {
        String host = "yourremotehost.com";
        int port = 1234; //Default SSL Port
        
        try {
            // Create SSLSocketFactory instance
            SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
            
            // Create SSLSocket instance
            SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
            
            // Initialize the socket's output stream 
            PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            // Write a message to the server
            output.println("Hello Server!");
            output.flush();
            
            // Initialize the socket's input stream 
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Read response from the server
            String response = input.readLine(); 
            System.out.println("Server response: " + response);
            
            // Close the streams and the socket
            output.close();
            input.close();
            socket.close();
            
        } catch(IOException e) {
            System.out.println("An error occurred : " + e.getMessage());
        }  
    }
}