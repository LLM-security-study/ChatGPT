import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req28 {
    private static final String REMOTE_HOST = "localhost";
    private static final int REMOTE_PORT = 1234;

    public static void main(String[] args) {
        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(REMOTE_HOST, REMOTE_PORT);

            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream())));
            out.println("Hello Server");
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            String response = in.readLine();
            System.out.println("Received: " + response);

            in.close();
            out.close();
            sslSocket.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}