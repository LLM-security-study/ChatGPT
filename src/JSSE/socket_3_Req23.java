import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req23 {
  public static void main(String[] args) {
      String hostname = "your server"; // Replace with your server
      int port = 1234; // Replace with your port
      
      try {
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) factory.createSocket(hostname, port);

        // Send request to the server
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.println("Hello Server");
        out.flush();

        // Receive response from the server
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = in.readLine();
        System.out.println("Received from server: " + line);

        in.close();
        out.close();
        socket.close();

      } catch (IOException e) {
          System.out.println("Failed to create SSL Socket");
          e.printStackTrace();
      }
  }
}