import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.cert.Certificate;

public class certificate_validation_1_Req16 {
  public static void main(String[] args) {
    String hostname = "localhost";
    int port = 443;
    try {
      // Create SSL Socket
      SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
      SSLSocket sslSocket = (SSLSocket) factory.createSocket(hostname, port);
      
      // Start handshake
      sslSocket.startHandshake();
      
      // Get server's certificates
      SSLSession session = sslSocket.getSession();
      Certificate[] serverCertificates = session.getPeerCertificates();
      
      // Check each one of them
      for (Certificate c : serverCertificates) {
        System.out.println(c);
        c.checkValidity(); // throws CertificateExpiredException or CertificateNotYetValidException if invalid
      }
      
      // Initiate IO streams
      PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
      
      // termination part of client
      out.close();
      in.close();
      sslSocket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}