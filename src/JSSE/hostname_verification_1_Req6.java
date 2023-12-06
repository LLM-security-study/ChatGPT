import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLException;
import java.net.HttpURLConnection;
import java.net.URL;

public class hostname_verification_1_Req6 {
    public static void main(String[] args) throws Exception {
        String urlString = "https://yoururlhere";  // replace with URL you want to connect

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
                public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                public void checkServerTrusted(X509Certificate[] certs, String authType) { }
            }
        };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                // verify whether hostname match with the hostname in server's certificate or not
                return true; // replace with your hostname verification logic
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        HttpURLConnection conn = (HttpURLConnection) new URL(urlString).openConnection();
        conn.connect();
        System.out.println("Response Code: " + conn.getResponseCode());
        conn.disconnect();
    }
}