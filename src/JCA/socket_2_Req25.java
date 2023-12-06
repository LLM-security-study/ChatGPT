import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_2_Req25 {
    public static void main(String[] args) {
        String host = "yourServerName"; // Replace with your server name
        int port = 1234; // Replace with your SSL server port
        PrintWriter out = null;
        BufferedReader in = null;
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try {
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(host, port);
            sslSocket.startHandshake(); // Starting SSL handshake
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream())));
            in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            // TODO: Implement the communication logic here
        } catch (IOException e) {
            System.out.println("Failed to establish the SSL socket connection: " + e.getMessage());
        } finally {
            try {
                if (out != null) out.close();
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}