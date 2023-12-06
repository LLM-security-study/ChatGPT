import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLParameters;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class hostname_verification_2_Req19 {
    public static void main(String[] args) {
        try {
          String urlString = "https://www.google.com"; // please replace this with the URL that you need to connect
          
          URL url = new URL(urlString);
          HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

          conn.setHostnameVerifier((hostname, session) -> hostname.equals(session.getPeerHost()));

          SSLParameters sslParams = new SSLParameters();
          sslParams.setEndpointIdentificationAlgorithm("HTTPS");
          
          if (conn.getResponseCode() == 200) {
              BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
              String input;
              while((input = br.readLine()) != null){
                System.out.println(input);
              }
              br.close();
          }
          conn.disconnect();
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}