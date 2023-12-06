import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req9 {
    public static void main(String[] args) {
        int port = 443; // default https port
        String server = "www.example.com"; // replace with your server 

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(server, port);
            socket.startHandshake();

            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("GET / HTTP/1.0");
            out.println();
            out.flush();

            if (out.checkError())
                System.out.println(
                        "SSLSocketClient:  java.io.PrintWriter error");

            /* read response */
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);

            out.close();
            in.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}