import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req17 {
    public static void main(String[] args) {
        String host = "your_server.com";
        int port = 1234;
        try {
            // create SSL socket and connect it to the remote server
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket)sslSocketFactory.createSocket(host, port);
            
            // create input and output streams
            PrintWriter out = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            
            // send request to the server
            out.println("Your request here...");
            out.flush();
            
            // read response from the server
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Received: " + line);
            }

            // clean up
            in.close();
            out.close();
            sslSocket.close();
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }
}