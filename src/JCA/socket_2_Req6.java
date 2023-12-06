import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_2_Req6 {
    public static void main(String[] args) {
        String serverName = "localhost"; // replace with your server name or IP
        int port = 1234; // replace with your server port

        try {
            SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(serverName, port);

            PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            // Add your communication logic here
            // You can use `out.println(<message>)` to send messages to the server
            // and `String response = in.readLine()` to read a message from the server

            out.close();
            in.close();
            sslSocket.close();
        } catch (IOException e) {
            System.err.println("I/O error in SSL connection setup: " + e);
        } 
    }
}