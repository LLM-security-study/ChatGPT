import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req13 {
    public static void main(String[] args) {
        String remoteHost = "localhost";  // remote host
        int remotePort = 1234;            // remote port number

        try {
            SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) socketFactory.createSocket(remoteHost, remotePort);

            PrintWriter out = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            out.println("Hello, server!");
            out.flush();

            String response = in.readLine();
            System.out.println("Server response: " + response);

            sslSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}