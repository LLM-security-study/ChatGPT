import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.cert.Certificate;

public class certificate_validation_2_Req29 {

   public static void main(String[] argv) {

      try {
         SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
         SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("localhost", 9999);

         // Start handshake
         sslsocket.startHandshake();

         // Get session after the connection is established
         SSLSession sslSession = sslsocket.getSession();

         // Print certification information
         System.out.println("SSLSession :");
         System.out.println("\tProtocol : " + sslSession.getProtocol());
         System.out.println("\tCipher suite : " + sslSession.getCipherSuite());

         // Get the certificates in use
         Certificate[] serverCertificates = sslSession.getPeerCertificates();

         for (Certificate certificate : serverCertificates) {
            System.out.println("Certificates: ");
            System.out.println("\t" + certificate.toString());
         }
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}