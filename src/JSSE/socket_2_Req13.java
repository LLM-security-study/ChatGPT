import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class socket_2_Req13 {
  private static final String HOST_NAME = "YourHost.com";
  private static final int PORT = 1234;

  public static void main(String[] args) {
    try {
      // Get the SSLSocketFactory
      SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

      // SSL handshake
      SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(HOST_NAME, PORT);

      // Create reader/writer
      PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

      // Write data to the server
      out.println("Hello Server!");

      // Read data from the server
      String response = in.readLine();
      System.out.println("Server response: " + response);

      // Close the connection
      in.close();
      out.close();
      sslSocket.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}