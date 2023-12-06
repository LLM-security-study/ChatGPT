import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;

class SSLClient {

    private InetAddress address;
    private int port;

    public SSLClient(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public void connectWithServer() throws Exception {
        
        // Create an SSL socket and connect it to the server.
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try (SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(this.address, this.port)) {

            // Create PrintWriter for sending data to server.
            PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);

            // Create a BufferedReader for reading server response.
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            // Sending request to the server.
            out.println("Hello Server!");

            // Reading server response.
            System.out.println("Response from server: " + in.readLine());
            
        } catch (Exception ex) {
            throw new Exception("Error connecting with server: " + ex.getMessage());
        }
    }
    
}

public class socket_1_Req3 {

    public static void main(String[] args) {
        try {
            // Creating SSLClient instance. Here, it's connecting with localhost at port 1234
            SSLClient sslClient = new SSLClient(InetAddress.getLocalHost(), 1234);

            // Start communication with the server
            sslClient.connectWithServer();
            
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
}