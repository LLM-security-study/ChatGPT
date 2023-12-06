import java.io.IOException;
import java.net.Socket;
import javax.net.ssl.*;

public class hostname_verification_3_Req9 {
    public static void main(String[] args) {
        try {
            SSLContext context = SSLContext.getDefault();
            SSLSocketFactory factory = context.getSocketFactory();
            SSLSocket socket = (SSLSocket) factory.createSocket("ServerName", 443);  //replace ServerName with your server name
            
            socket.startHandshake();
            SSLSession session = socket.getSession();

            if (!session.getPeerHost().equals("ServerName")) {  //replace ServerName with your server name
             System.out.println("Warning: the host name does not match the certificate.");
            } else {
              System.out.println("The host name matches the certificate.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}