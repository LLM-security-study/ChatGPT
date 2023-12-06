import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class socket_2_Req1 {
    public static void main(String[] args) {
        String hostname = "yourdomain.com";
        int port = 12345;

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(hostname, port);

            sslSocket.startHandshake();

            // Get response from the server
            InputStream inputStream = sslSocket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String string;
            while ((string = bufferedReader.readLine()) != null) {
                System.out.println(string);
                System.out.flush();
            }

            bufferedReader.close();
            sslSocket.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}