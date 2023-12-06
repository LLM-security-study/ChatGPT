import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req29 {
    public static void main(String[] args) {
        String remoteServer = "YourRemoteServer.com"; // replace with your remote server
        int remotePort = 1234; // replace with your port

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(remoteServer, remotePort);

            PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Let's just send a simple message to the server
            output.println("Hello Server");
            output.flush();

            // Let's read the response
            String response = input.readLine();
            System.out.println("Server Response: " + response);

            // Close streams and socket
            output.close();
            input.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Unable to establish SSL socket connection");
            e.printStackTrace();
        }
    }
}