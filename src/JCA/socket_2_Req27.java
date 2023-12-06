import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_2_Req27 {
    public static void main(String[] args) {
        String remoteServer = "www.example.com";
        int port = 443; // Default port for SSL

        try {
            SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket)factory.createSocket(remoteServer, port);

            PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            output.println("Hello Server!");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Response from server: " + input.readLine());

            output.close();
            input.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Failed to create socket: " + e.getMessage());
            e.printStackTrace();
        }
    }
}