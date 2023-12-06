import javax.net.ssl.*;

public class certificate_validation_3_Req11 {

    public static void main(String[] args) {
        
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");

        try {
            // specify the host and port 
            String host = "<host>";
            int port = <port>;
            
            // get the SSLContext object 
            SSLContext context = SSLContext.getInstance("TLS");

            // initialize the context 
            context.init(null, null, null);

            // Create SSLSocketFactory object
            SSLSocketFactory ssf = context.getSocketFactory();
            
            // Get the SSLSocket
            SSLSocket socket = (SSLSocket) ssf.createSocket(host, port);

            // start the handshake
            socket.startHandshake();
      
            // Get session after the connection is established
            SSLSession sslSession = socket.getSession();

            // print the certificate information
            System.out.println("Peer Host : " + sslSession.getPeerHost());
            System.out.println("Cipher suite : " + sslSession.getCipherSuite());
            System.out.println("Protocol : " + sslSession.getProtocol());

            // Get and print the SSLSession certificate
            java.security.cert.Certificate[] serverCerts = sslSession.getPeerCertificates();
            for (int i = 0; i < serverCerts.length; i++) {
                System.out.printf("%s %s : \n%s\n", serverCerts[i].getType(),
                        serverCerts[i].getPublicKey(),
                        serverCerts[i].toString());
            }

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}