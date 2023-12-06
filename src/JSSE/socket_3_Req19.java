import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req19 {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 9090;
        BufferedReader bufferedReader;
        SSLSocket sslSocket;

        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            sslSocket = (SSLSocket) sslSocketFactory.createSocket(host, port);

            PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String userInput;

            while ((userInput = bufferedReader.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }

            out.close();
            in.close();
            sslSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}