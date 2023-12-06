import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class socket_3_Req17 {
    private static final String SERVER = "your.server.com";
    private static final int PORT = 1234; 

    public static void main(String[] args) throws Exception {
        SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        SSLSocket sslSocket = (SSLSocket)factory.createSocket(SERVER, PORT);

        PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), false);
        BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

        String message = "Hello server!";
        out.println(message);
        out.flush(); 

        System.out.println("Server said: " + in.readLine());

        out.close();
        in.close();
        sslSocket.close();
    }
}