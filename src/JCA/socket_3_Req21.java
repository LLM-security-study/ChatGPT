import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req21 {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 1234;

        try {
            // Obtain a SSLSocketFactory 
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            // Creates a SSLSocket and starts out as a client to a specified host and port
            SSLSocket socket = (SSLSocket) factory.createSocket(hostname, port);

            // Start handshaking
            socket.startHandshake();

            // Get the input and output streams
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())));
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            // Do your stuff here...
            // Don't forget to close in and out streams

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}