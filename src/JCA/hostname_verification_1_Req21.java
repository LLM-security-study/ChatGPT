import java.io.IOException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.HostnameVerifier;

public class hostname_verification_1_Req21 {

    public static void main(String[] args) {
        String hostname = "www.yourwebsite.com";
        int port = 443;

        try {
            SSLSocketFactory factory =
                (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket =
                (SSLSocket) factory.createSocket(hostname, port);

            // initiate the SSL handshake
            socket.startHandshake();

            // verify the host
            SSLSession sslSession = socket.getSession();
            String actualHost = sslSession.getPeerHost();

            HostnameVerifier hostnameVerifier =
                    HttpsURLConnection.getDefaultHostnameVerifier();
            boolean verify = hostnameVerifier.verify(hostname, sslSession);

            if (verify) {
                System.out.println("Hostname verified.");
            } else {
                System.out.println("Hostname verification failed!");
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}