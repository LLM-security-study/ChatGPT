import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_2_Req16 {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 443;

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket = (SSLSocket) factory.createSocket(host, port);

            // Initiates the handshake
            sslsocket.startHandshake();

            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sslsocket.getOutputStream())));
            BufferedReader in = new BufferedReader(new InputStreamReader(sslsocket.getInputStream()));

            // Send data to the server
            out.println("Hello, Server!");
            out.flush();

            // Read server response
            String response = in.readLine();
            System.out.println("Received: " + response);

            out.close();
            in.close();
            sslsocket.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}