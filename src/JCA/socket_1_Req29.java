import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req29 {

    public static void main(String[] args) {
        try {
            SSLSocketFactory factory =
                    (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) factory.createSocket("server_url", 1234);

            sslSocket.startHandshake();

            PrintWriter out = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            out.println("Hello Server");
            out.flush();

            System.out.println("Message from server: " + in.readLine());

            in.close();
            out.close();
            sslSocket.close();

        } catch (IOException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}