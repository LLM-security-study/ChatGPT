import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req28 {
    public static void main(String[] args) throws IOException {
        // Specify host name and port number
        String hostName = "localhost";
        int portNumber = 12345;

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket = (SSLSocket) factory.createSocket(hostName, portNumber);
            
            // SSL handshake
            sslsocket.startHandshake();
            
            // Communicate with the server
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sslsocket.getOutputStream())));
            out.println("Hello, Server!");
            out.flush();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(sslsocket.getInputStream()));
            System.out.println("Server response: " + in.readLine());

            // Close the connections
            in.close();
            out.close();
            sslsocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}