import javax.net.ssl.*;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.security.cert.Certificate;

public class certificate_validation_3_Req1 {
    public static void main(String[] args) throws Exception {
        System.setProperty("javax.net.debug", "all"); // Optional: use for debugging ssl

        URL url = new URL("https://yourURL.com"); 

        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.connect();

        Certificate[] certs = conn.getServerCertificates();
        System.out.println("Number of certs: " + certs.length);
        for(Certificate cert : certs){
            System.out.println(cert.toString());
        }

        Socket socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket("localhost", 443);
        
        socket.startHandshake();
        SSLSession session = socket.getSession();
        java.security.cert.Certificate[] servercerts = session.getPeerCertificates();

        for (int i = 0; i < servercerts.length; i++) {
            System.out.println("Certificate " + (i+1));
            System.out.println(servercerts[i]);
        }
    }
}