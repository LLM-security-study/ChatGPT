import javax.net.ssl.*;
import java.io.*;

public class socket_3_Req11 {

    public static void main(String[] args) {
        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("YourRemoteHostName", YourRemotePort);

            PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            // Send a request to the server 
            out.println("Hello Server");
            
            // Reading server reply  
            String response = in.readLine();
            System.out.println("Server's response: " + response);

            // Close the socket
            in.close();
            out.close();
            sslSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}