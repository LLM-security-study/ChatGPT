import javax.net.ssl.*;
import java.io.*;

public class socket_3_Req30 {

    public static void main(String[] args) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket("host", port);

            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // you can write and read from the socket as per the server's protocol
            out.println("Hello Server");
            out.flush();

            String response = in.readLine();
            System.out.println("Server response: " + response);

            // close the streams and socket
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Failed to create SSL socket.");
            e.printStackTrace();
        }
    }
}