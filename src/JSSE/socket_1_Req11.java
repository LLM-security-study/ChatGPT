import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class socket_1_Req11 {

    // specify server details
    private static final String HOST = "localhost";
    private static final int PORT = 1234;

    public static void main(String[] args) {
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslSocket = null;
      
        try {
            sslSocket = (SSLSocket) sslSocketFactory.createSocket(HOST, PORT); 
            
            //create input stream for reading from server
            InputStream inputstream = sslSocket.getInputStream();
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
            
            String string = null;
            while ((string = bufferedreader.readLine()) != null) {
                System.out.println(string);
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (sslSocket != null) {
                    sslSocket.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing socket: " + e.getMessage());
            }
        }
    }
}