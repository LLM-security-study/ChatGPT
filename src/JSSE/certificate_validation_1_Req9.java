import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.net.URL;

public class certificate_validation_1_Req9 {

    public static void main(String[] args) {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {     
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() { 
                        return null;
                    } 
                    public void checkClientTrusted( 
                        java.security.cert.X509Certificate[] certs, String authType) {
                    } 
                    public void checkServerTrusted( 
                        java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
            }; 

            SSLContext sc = SSLContext.getInstance("SSL"); 
            sc.init(null, trustAllCerts, new java.security.SecureRandom()); 
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            URL url = new URL("https://yourdesiredurl.com"); 
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.getResponseCode(); 

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}