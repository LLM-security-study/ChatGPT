import javax.net.ssl.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class hostname_verification_1_Req26 {
    final String host;
    final int port;

    public hostname_verification_1_Req26(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: java hostname_verification_1_Req26 <hostname> <port>");
            System.exit(-1);
        }

        new hostname_verification_1_Req26(args[0], Integer.parseInt(args[1])).startCommunication();
    }

    void startCommunication() throws NoSuchAlgorithmException {
        // Get a SSL context
        SSLContext context = SSLContext.getDefault();

        // Create a socket factory
        SSLSocketFactory factory = context.getSocketFactory();

        // Create a socket
        try(SSLSocket socket = (SSLSocket) factory.createSocket(host, port)) {

            // Get the hostname verification from HttpsURLConnection
            HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();

            // Create a SSL session and initiate handshake
            SSLSession session = socket.getSession();

            if(!hv.verify(host, session))
                throw new SSLHandshakeException("Server identity cannot be verified!");

            performActionAfterSSLContextInitialized(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void performActionAfterSSLContextInitialized(SSLSocket socket) {
        /* Add code here to do something with this socket,
           such as read/write communications, etc. */
    }
}