import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_2_Req30 {
    public static void main(String[] args) {
        String host = "your.remote.server.com";  // replace with your actual server domain
        int port = 1234;  // replace with the actual port number
        try {
            SSLSocketFactory factory =
                    (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(host, port);

            PrintWriter out = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            String message = "Hello, server!";
            out.println(message);
            out.flush();

            String serverResponse = in.readLine();
            System.out.println("Server responded with: " + serverResponse);

            // Don't forget to close resources when you're done!
            out.close();
            in.close();
            sslSocket.close();
        } catch (IOException e) {
            System.out.println("Error occurred " + e.getMessage());
        }
    }
}