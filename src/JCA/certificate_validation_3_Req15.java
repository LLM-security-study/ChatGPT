import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;

public class certificate_validation_3_Req15 {
    public static void main(String[] args) {
        BufferedReader in = null;
        PrintWriter out = null;
        Socket clientSocket = null;
        try{
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            clientSocket = sslSocketFactory.createSocket("HOST_NAME", PORT_NUMBER);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            // perform input/output operations using 'in' and 'out'
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (clientSocket != null) clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}