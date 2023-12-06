import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req13 {

    public static void main(String[] args) throws IOException {
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslSocket = (SSLSocket) factory.createSocket("ServerIp", ServerPort); // replace "ServerIp" and ServerPort with your server info

        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream())));
        out.println("Hello Server"); // Sends "Hello Server" to the server
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream())); // retrieving data from the server
        System.out.println("Server response: " + in.readLine());

        out.close();
        in.close();
        sslSocket.close();
    }
}