import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_2_Req23 {

    private static final String SERVER_HOST = "Your-Server-Address";
    private static final int SERVER_PORT = 9000; // Replace with the SSL Port of your server

    public static void main(String[] args) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(SERVER_HOST, SERVER_PORT);

            PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            //Passing the information to the server
            out.println("Hello Server!");

            //Receiving server response
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Server response: " + inputLine);
            }

            //Closing the streams and the socket
            out.close();
            in.close();
            sslSocket.close();

        } catch (IOException e) {
            System.out.println("Failed to create SSL socket : " + e.getMessage());
            e.printStackTrace();
        }
    }
}