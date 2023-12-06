import java.io.*;
import javax.net.ssl.*;

public class socket_1_Req16 {
    public static void main(String[] args) {
        String hostname = "localhost";    // replace with your target server hostname
        int port = 9999;          // replace with your target server port

        SSLSocketFactory ssf = (SSLSocketFactory)SSLSocketFactory.getDefault();

        try {
            SSLSocket socket = (SSLSocket)ssf.createSocket(hostname, port);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Specify the requirement of client authentication
            socket.setNeedClientAuth(true);
       
            out.println("Hello Server"); // send a message to the server
            out.flush();

            System.out.println("Received from server: " + in.readLine());  // read server's response

            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}