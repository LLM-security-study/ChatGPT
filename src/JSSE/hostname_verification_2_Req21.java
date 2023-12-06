import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_2_Req21 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.example.com");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(url.getHost(), 443);

            SSLParameters sslParams = new SSLParameters();
            sslParams.setEndpointIdentificationAlgorithm("HTTPS");
            sslSocket.setSSLParameters(sslParams);

            sslSocket.startHandshake();

            boolean isConnected = sslSocket.isConnected();
            boolean isVerified = sslSocket.getSession().isValid();

            System.out.println("Host Connected: "+isConnected);
            System.out.println("Hostname Verified: "+isVerified);

            sslSocket.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
