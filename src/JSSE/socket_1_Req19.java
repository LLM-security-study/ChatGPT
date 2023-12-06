import java.io.*;
import javax.net.ssl.*;

public class socket_1_Req19 {

   public static void main(String[] args) {
      System.setProperty("javax.net.ssl.trustStore","mySrvKeystore");
      System.setProperty("javax.net.ssl.trustStorePassword","123456");

      try {

         SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
         SSLSocket sslSocket = (SSLSocket) sslsocketfactory.createSocket("localhost", 9999);

         PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
         BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

         out.println("Hello Server!");
         System.out.println("Server replied:" + in.readLine());

         out.close();
         in.close();
         sslSocket.close();
            
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}