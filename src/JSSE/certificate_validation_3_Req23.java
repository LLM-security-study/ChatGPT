import javax.net.ssl.*;
import java.io.FileInputStream;
import java.security.KeyStore;

public class certificate_validation_3_Req23 {

    public static void main(String[] args) {
        try {
            // Load the JKS keyStore containing the server certificate (public+private keys)
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream("path-to-your-keystore"), "your-keystore-password".toCharArray());

            // KeyManager decides which certification to use for authentication
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, "your-key-password".toCharArray());

            // Create and initialize SSLContext for TLS
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(kmf.getKeyManagers(), null, null);

            // Create SSLServerSocket
            SSLServerSocketFactory ssf = sc.getServerSocketFactory();
            SSLServerSocket s = (SSLServerSocket) ssf.createServerSocket(8888);

            System.out.println("Server started:");
            printServerSocketInfo(s);

            // Waiting for connection from clients
            SSLSocket c = (SSLSocket) s.accept();
            printSocketInfo(c);

            // Communicate with the client

            // Close when you are done with the communication
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printServerSocketInfo(SSLServerSocket s) {
        System.out.println("Server socket class: "+s.getClass());
        System.out.println("   Socket address = "+s.getInetAddress().toString());
        System.out.println("   Socket port = "+s.getLocalPort());
        System.out.println("   Need client authentication = "+s.getNeedClientAuth());
        System.out.println("   Want client authentication = "+s.getWantClientAuth());
    }

    private static void printSocketInfo(SSLSocket s) {
        System.out.println("Socket class: "+s.getClass());
        System.out.println("   Remote address = "+s.getInetAddress().toString());
        System.out.println("   Remote port = "+s.getPort());
        System.out.println("   Local socket address = "+s.getLocalSocketAddress().toString());
    }
  
}