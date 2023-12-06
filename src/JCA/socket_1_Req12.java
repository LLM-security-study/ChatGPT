import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req12 {
   public static void main(String[] args) {
       try {
           SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
           SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("localhost", 9999);

           PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
           BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

           // Send request to the server
           String request = "Hello Server";
           out.println(request);

           // Get response from the server
           String response = in.readLine();
           System.out.println("Server response: " + response);

           // Close IO streams then socket
           out.close();
           in.close();
           sslSocket.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}