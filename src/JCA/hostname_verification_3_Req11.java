import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_3_Req11 {
  public static void main(String[] args) {
    try {
      URL url = new URL("https://yoururlhere.com");
      HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
      
      SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
      SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(url.getHost(), 443);
      
      SSLParameters sslParams = new SSLParameters();
      sslParams.setEndpointIdentificationAlgorithm("HTTPS");
      sslSocket.setSSLParameters(sslParams);

      sslSocket.startHandshake();
      boolean connected = sslSocket.isConnected();
      
      System.out.println("Hostname verification result: " + connected); // true indicates successful verification
      sslSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}