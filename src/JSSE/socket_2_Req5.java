import java.io.*;
import javax.net.ssl.*;

public class socket_2_Req5 {
    public static void main(String[] args) {
        try {
            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslsocketfactory.createSocket("Hostname", 443);

            PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            out.println("Hello Server");
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
            }

            out.close();
            in.close();
            sslSocket.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}