import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.*;

public class socket_2_Req8{
    public static void main(String[] args){
        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("RemoteHostName", 9999);

            PrintWriter out = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            // Send a message to the server
            out.println("Hello Server");
            out.flush();

            // Read the server's response
            String line;
            while ((line = in.readLine()) != null){
                System.out.println(line);
            }

            out.close();
            in.close();
            sslSocket.close();

        } catch (IOException e) {
            System.out.println("Failed to establish SSL connection to server: " + e.getMessage());
        }
    }
}