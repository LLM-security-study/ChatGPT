import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_2_Req29 {
    private static final String REMOTE_SERVER = "hostname"; // replace with your remote server
    private static final int PORT = 1234; // replace with the port number

    public static void main(String[] args) {
        try {
            // Obtain a SSLSocketFactory object using getSocketFactory method
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            // Create a SSLSocket using the factory object and connect to the remote server
            SSLSocket socket = (SSLSocket) factory.createSocket(REMOTE_SERVER, PORT);

            // Create input and output streams to read from and write to the server
            InputStream inputstream = socket.getInputStream();
            OutputStream outputstream = socket.getOutputStream();

            // Replace with your application specific logic of communication with server.
            // Write something to the outputstream, send to the server.
            outputstream.write("Hello Server".getBytes());
            outputstream.flush();

            // Read the response from the server.
            BufferedReader response = new BufferedReader(new InputStreamReader(inputstream));
            String line;
            while ((line = response.readLine()) != null) {
                System.out.println("Server response: " + line);
            }

            // Always be sure to close the socket when you're done
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}