import java.io.*;
import javax.net.ssl.*;

public class socket_2_Req9 {
  public static void main(String[] args) {
    String serverName = "example.com"; // Insert your server name here
    int port = 1234; // Insert your port number here

    System.setProperty("javax.net.ssl.trustStore", "myTrustStore.jts");
    
    try {
      SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
      SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(serverName, port);

      InputStream inputstream = sslsocket.getInputStream();
      InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
      BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

      String rawInput = null;
      while ((rawInput = bufferedreader.readLine()) != null) {
        System.out.println(rawInput);
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}