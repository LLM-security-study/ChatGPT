import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.Socket;

public class hostname_verification_2_Req14 {

    public static void main(String[] args) {
        String hostname = "your_host_here"; // Set your hostname here

        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            SSLSocket socket = (SSLSocket) factory.createSocket(hostname, 443); // 443 is the standard port for https

            socket.startHandshake();

            SSLSession session = socket.getSession();
            java.security.cert.Certificate[] servercerts = session.getPeerCertificates();

            for (int i = 0; i < servercerts.length; i++) {
                System.out.println("Certificate " + (i+1) + ":");
                System.out.println("\tPublic Key: " + servercerts[i].getPublicKey());
                System.out.println("\tCertificate type: " + servercerts[i].getType());

                System.out.println("Cert Principal : " + ((java.security.cert.X509Certificate) servercerts[i]).getSubjectDN());
                System.out.println("Cert Issuer Principal : " + ((java.security.cert.X509Certificate) servercerts[i]).getIssuerDN());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}