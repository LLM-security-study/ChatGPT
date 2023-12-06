import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class certificate_validation_3_Req10 {
    public static void main(String[] args) {
        String hostname = "example.com";  // replace with your server hostname or IP
        int port = 443;  // replace with your server port

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(hostname, port);

            socket.startHandshake();

            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            out.println("GET / HTTP/1.0");
            out.println();
            out.flush();

            if (out.checkError())
                System.out.println("SSLSocketClient:  java.io.PrintWriter error");

            /* read response */
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);

            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}