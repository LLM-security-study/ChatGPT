import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req4 {

    private static final String HOST = "localhost";
    private static final int PORT = 1234;

    public static void main(String[] args) {
        socket_1_Req4 main = new socket_1_Req4();
        main.createSSLSocket();
    }

    private void createSSLSocket() {
        try {
            // Obtain a SSLSocketFactory object
            SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            // Create an SSLSocket
            SSLSocket socket = (SSLSocket) socketFactory.createSocket(HOST, PORT);

            // Create input and output streams to the server
            PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send request to the server, in your case, replace "request" with your real request
            output.println("request");
            output.flush();

            // Get response from the server
            String response = input.readLine();
            System.out.println("Server response: " + response);

            // Close connection
            output.close();
            input.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}