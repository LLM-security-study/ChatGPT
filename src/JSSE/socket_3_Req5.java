import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;

public class socket_3_Req5 {
    
    public static void main(String[] args) {
        new socket_3_Req5().createSslSocket("<server>", "<port>");
    }
    
    private void createSslSocket(String server, int port) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(server, port);

            sslSocket.startHandshake();
            System.out.println("SSL Socket successfully created and secure connection established");
        } catch (IOException e) {
            System.err.println("Problem creating SSL Socket");
            e.printStackTrace();
        }
    }
}