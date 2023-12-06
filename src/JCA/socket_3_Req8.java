import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req8 {
    public static void main(String[] args) {
        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("YourHostName", 443); 

            PrintWriter out = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            //You can use 'out' to send requests to the server and 'in' to get the response from the server over the SSL Socket
            
            // Close streams
            out.close();
            in.close();

        } catch (IOException ex) {
            System.err.println("Failed to create SSL Socket: " + ex);
        }
    }
}