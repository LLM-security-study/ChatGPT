import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_2_Req7 {
    private static final String REMOTE_HOST = "yourRemoteHost";  // Replace this with your remote host
    private static final int REMOTE_PORT = 1234;  // Replace this with your remote port

    public static void main(String[] args) {
        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(REMOTE_HOST, REMOTE_PORT);

            PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            // Send a message to the server
            out.println("Hello Server");
            // Read and print the server's response
            String response = in.readLine();
            System.out.println("Server response: " + response);

            // Close the I/O streams
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}