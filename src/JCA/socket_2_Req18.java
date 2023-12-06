import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_2_Req18 {
    public static void main(String[] args) {
        String remoteServer = "server.domain.com"; // replace with your server address
        int port = 443; // replace with your port number

        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(remoteServer, port);

            // create reader for reading server responses
            InputStreamReader inputStreamReader = new InputStreamReader(sslSocket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            
            bufferedReader.close();
            sslSocket.close();
        } catch (IOException e) {
            System.out.println("Error establishing SSL socket connection: " + e.getMessage());
        }
    }
}