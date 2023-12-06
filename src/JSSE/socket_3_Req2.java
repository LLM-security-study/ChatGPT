import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req2 {
    public static void main(String[] args) {
        try {
            // Getting the SSL Socket Factory
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            // Creating an SSL socket
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("www.yoursite.com", 443);

            // Preparing the Input / Output streams
            PrintWriter out = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            // A simple request (for example)
            out.println("GET / HTTP/1.0");
            out.println();
            out.flush();

            // Reading response
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            // Closing the streams
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}