import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.HostnameVerifier;

public class hostname_verification_1_Req19 {
    // Set your hostname here
    private static final String HOSTNAME = "www.example.com";

    public static void main(String[] args) throws Exception {
        installHostnameVerifier();

        HttpsURLConnection connection = null;
        URL url = new URL("https://" + HOSTNAME);
        connection = (HttpsURLConnection) url.openConnection();
        // You can add the necessary code under openConnection.
    }

    private static void installHostnameVerifier() {
        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            HostnameVerifier allHostsValid = (hostname, session) -> {
                if (hostname.equals(HOSTNAME)) {
                    return true;
                }
                return false;
            };
            sc.init(null, null, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            System.out.println("Error installing hostname verifier: " + e.getMessage());
        }
    }
}