import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

public class certificate_validation_3_Req9 {
 public static void main(String[] args) throws Exception {
   // Specify the URL
   String urlString = "https://www.google.com";

   URL url = new URL(urlString);
   URLConnection urlConnection = url.openConnection();

   // Validate if it is HTTPS protocol, we need to validate specifically the SSL Cert.
   if (urlConnection instanceof HttpsURLConnection) {
     HttpsURLConnection httpsUrlConnection = (HttpsURLConnection) urlConnection;
     // Validate Hostname and Server Name
     HostnameVerifier hostnameVerifier = new CustomizedHostnameVerifier();
     httpsUrlConnection.setHostnameVerifier(hostnameVerifier);

     // Get Response from Server
     BufferedReader bufferedReader = new BufferedReader(
         new InputStreamReader(httpsUrlConnection.getInputStream()));
     String line = null;
     while ((line = bufferedReader.readLine()) != null) {
       System.out.println(line);
     }
   }
 }

 static class CustomizedHostnameVerifier implements HostnameVerifier {

   public boolean verify(String urlHostName, SSLSession session) {
     System.out.println("URL Host: " + urlHostName + " vs Cert Host: "
         + session.getPeerHost());

     // Check if urlHostName is in the server's Common Name
     return HttpsURLConnection.getDefaultHostnameVerifier().verify(urlHostName, session);
   }
 }
}