import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.net.URL;

class hostname_verification_3_Req24 {
    public static void main(String[] args) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();

            SSLSocket socket = (SSLSocket)factory.createSocket("www.example.com", 443);
            String[] suites = socket.getSupportedCipherSuites();
            socket.setEnabledCipherSuites(suites);
            socket.startHandshake();

            SSLSession session = socket.getSession();
            String host = session.getPeerHost();

            URL url = new URL("https://" + host);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setHostnameVerifier((hostname, session1) -> hostname.equals(host));
            System.out.println("Is Hostname Verified: " + (connection.getResponseCode() == 200));

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}