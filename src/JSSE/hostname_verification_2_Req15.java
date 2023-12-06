import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

public class hostname_verification_2_Req15 {
    public static void main(String[] args) {
        try {
            SSLSocketFactory factory = HttpsURLConnection.getDefaultSSLSocketFactory();
            SSLSocket socket = (SSLSocket)factory.createSocket(args[0], Integer.parseInt(args[1]));

            socket.startHandshake();
            
            SSLSession session = socket.getSession();
            HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
            if (!hv.verify(args[0], session)) {
                throw new SSLException("Hostname verification failed");
            }
            
            // Connection is established
            System.out.println("Hostname verification is successful: " + args[0]);
            socket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}