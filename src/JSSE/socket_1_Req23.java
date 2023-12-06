import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req23 {
    public static void main(String[] args) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) factory.createSocket("Your_Server_Hostname", Your_Server_Port);

            // Create streams to securely send and receive data to the server
            InputStream in = sslSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            OutputStream out = sslSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out));

            // Write a message to the server
            writer.println("Hello Server");
            writer.flush();

            // Read the response from the server
            String response = reader.readLine();
            System.out.println("Server Response: " + response);

            // Close the SSL socket
            sslSocket.close();
        } catch (IOException ex) {
            System.out.println("Failed to create SSL socket : " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}