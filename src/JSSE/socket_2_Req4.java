import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_2_Req4 {
    public static void main(String[] args) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket)factory.createSocket("localhost", 9999); //"localhost" should be replaced by your server's address and 9999 by your server's port
            sslSocket.startHandshake();

            PrintWriter out = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            out.println("Hello Server");
            out.flush();

            System.out.println("Message to server sent, reading server's response");
            System.out.println("Server's response: " + in.readLine());

            sslSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}