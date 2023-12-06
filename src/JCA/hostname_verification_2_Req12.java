import javax.net.ssl.*;
import java.net.InetAddress;

public class hostname_verification_2_Req12 {
    public static void main(String[] args) {
        String hostname = "example.com"; // specify the correct hostname

        try {
            // create SSL socket and start handshake
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(hostname, 443);
            socket.startHandshake();

            // get the SSL session after handshake
            SSLSession session = socket.getSession();
            
            // check if the peer's hostname matches with what SSL thinks it is
            if (!hostname.equals(session.getPeerHost())) {
                throw new SSLPeerUnverifiedException("Expected " + hostname + ", got " + session.getPeerHost());
            }
            
            // check if the hostname is an IP address, if not it have to match 
            if (InetAddress.getByName(hostname).getHostAddress().equals(hostname)) {
                System.out.println("Hostname is an IP address, no further checks needed");                
            } else if (!javax.net.ssl.HttpsURLConnection.getDefaultHostnameVerifier().verify(hostname, session)) {
                throw new SSLPeerUnverifiedException("Hostname " + hostname + " not verified:");
            } else {
                System.out.println("Hostname verified!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}