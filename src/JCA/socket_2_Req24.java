import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class socket_2_Req24 {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 8443; // Change this to your SSL port

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(host, port);
            sslSocket.startHandshake();

            PrintWriter out = new PrintWriter(sslSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            // Write a message to the server
            out.println("Hello Server!");
            out.flush();

            // Read the response from the server
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            // Don't forget to close the input/output streams and socket when done.
            out.close();
            in.close();
            sslSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}