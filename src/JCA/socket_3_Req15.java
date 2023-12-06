import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req15 {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 9999; // or port of your server

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(host, port);
            
            //Enabling SSL handshake debugging
            System.setProperty("javax.net.debug", "ssl:handshake");

            PrintWriter out = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            out.println("Hello Server");
            out.println("QUIT");
            out.flush();

            // Reading response from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println(msg);
            }
            
            out.close();
            in.close();
            sslSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}