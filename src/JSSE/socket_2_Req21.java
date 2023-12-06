import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_2_Req21 {
    public static void main(String[] args) {
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        try {
            SSLSocket sslSocket = (SSLSocket)sslSocketFactory.createSocket("YourRemoteServerName", 443);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            // Send request to the server
            out.println("YourRequest");
            out.println();
            out.flush();
            // Get response from server
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            String x;
            while ((x = in.readLine()) != null) {
                System.out.println(x);
            }
            sslSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}