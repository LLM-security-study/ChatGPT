import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req8 {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 25565;

    public static void main(String[] args) throws IOException {
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslSocket = null;
        try {
            sslSocket = (SSLSocket) sslSocketFactory.createSocket(SERVER_IP, SERVER_PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));

            String line = reader.readLine();
            while (line != null) {
                System.out.println("Received from server: " + line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("I/O error occurred while reading/writing socket data.");
            e.printStackTrace();
        } finally {
            if (sslSocket != null && sslSocket.isConnected()) {
                try {
                    sslSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}