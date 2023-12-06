import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.HostnameVerifier;

class hostname_verification_3_Req5 {
    
    static class MyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
            if ("expected.hostname.com".equals(hostname)) {
                return true;
            }
            return false;
        }
    }
    
    public static void main(String[] args) {
        try {
            // Create a URL object
            URL url = new URL("https://expected.hostname.com:port");
            
            // Open a connection to the URL
            URLConnection connection = url.openConnection();
            
            if(connection instanceof HttpsURLConnection) {
                ((HttpsURLConnection) connection).setHostnameVerifier(new MyHostnameVerifier());
            }
            
            // Connect to the URL
            connection.connect();
            
            System.out.println("Successfully connected to the server.");
        }
        catch (Exception e) {
            System.out.println("Failed to connect to the server.");
            e.printStackTrace();
        }
    }
}