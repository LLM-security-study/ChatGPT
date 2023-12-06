import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req20 {
    public static void main(String[] args){
        String host = "localhost"; 
        int port = 8080; 
        try{
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(host, port);

            // Enable the necessary protocols 
            String[] protocols = socket.getSupportedProtocols();
            socket.setEnabledProtocols(protocols);

            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Send a message to the server
            String message = "Hello Server!";
            writer.println(message);
            writer.flush();
            
            String serverResponse = reader.readLine();
            System.out.println("Server responded with: " + serverResponse);
            
            // Close the connections
            reader.close();
            writer.close();
            socket.close();
         } catch (Exception e){
             e.printStackTrace();
         }
    }
}