import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.net.*;
import java.io.*;

public class hostname_verification_3_Req14 {
    public static void main(String[] args) throws Exception {
        String hostname = "www.example.com"; // replace with your host
        int port = 443; // replace with your port

        SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) factory.createSocket(hostname, port);

        // initiate the SSL handshake
        socket.startHandshake();

        // verify the server's certificate
        SSLSession session = socket.getSession();
        java.security.cert.Certificate[] servercerts = session.getPeerCertificates();

        // get the hostname of the server certificate 
        String serverCertHostName = ((X509Certificate)servercerts[0]).getSubjectX500Principal().getName();

        System.out.println("Host Name: " + serverCertHostName);
 
        // verify the hostname
        if(!hostname.equals(serverCertHostName)) {
             throw new SSLHandshakeException("Expected " + hostname + ", got " + serverCertHostName);
        }
    }
}