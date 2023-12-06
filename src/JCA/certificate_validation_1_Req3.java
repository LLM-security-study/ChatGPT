import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class certificate_validation_1_Req3 {
    public static void main(String[] args) {
        int port = 8443; // port should be the same as the server 
        String server = "localhost"; // server can be ip address or domain name.
        
        System.setProperty("javax.net.ssl.trustStore", "keystore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");

        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(server, port);

            PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader( new InputStreamReader(sslSocket.getInputStream()));

            String userInput;
            while ((userInput = in.readLine()) != null) {
                System.out.println("Echo from server: " + userInput);
            }

            sslSocket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}