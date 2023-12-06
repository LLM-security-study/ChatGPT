import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req22 {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;
        sendReceive(host, port);
    }

    public static void sendReceive(String host, int port) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(host, port);
            
            PrintWriter out = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            out.println("Hello Secure World!");
            out.flush();
            
            String responseLine = in.readLine();
            System.out.println("Received: " + responseLine);
            
            out.close();
            in.close();
            sslSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}     