import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;
import java.net.URL;
import java.io.IOException;

public class hostname_verification_2_Req17 {
    public static void main(string[] args) throws IOException {
        // The URL of the server.
        String urlString = "https://example.com";
        URL url = new URL(urlString);

        // Create an HttpsURLConnection.
        HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection();

        // Create a HostnameVerifier.
        // The verify method of HostnameVerifier interface is called 
        // during the SSL handshake to verify the hostname.
        HostnameVerifier hostnameVerifier = new HostnameVerifier(){
             @Override
             public boolean verify(String hostname, SSLSession session) {
                // Check if the hostname matches "example.com".
                // If it does, return true. Otherwise, return false.
                // Note: You may want to improve this for production use.
                return "example.com".equals(hostname);
             }
        };

        // Set the hostnameVerifier into the HttpsURLConnection.
        urlConnection.setHostnameVerifier(hostnameVerifier);

        // Connect to the server and perform any other operations you need.    
        urlConnection.connect();
        
        // handle I/O or connection problems
        // ...
    }
}