import javax.net.ssl.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

public class certificate_validation_3_Req19 {
    public static void main(String[] args) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(args[0], Integer.parseInt(args[1]));

            socket.startHandshake();

            SSLSession session = socket.getSession();
            X509Certificate certificate = null;

            java.security.cert.Certificate[] serverCerts = session.getPeerCertificates();
            if (serverCerts.length == 1) {
                certificate = (X509Certificate) serverCerts[0];
            } 

            certificate.checkValidity();
            System.out.println("Certificate is valid.");

            socket.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (CertificateException e) {
            System.out.println("CertificateException: " + e.getMessage());
        }
    }
}