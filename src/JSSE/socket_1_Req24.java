import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req24 {
    public static void main(String[] arstring) {
        try {
            System.setProperty("javax.net.ssl.trustStore","myTrustStore.jts");
            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("localhost", 9999);

            PrintWriter output = new PrintWriter(new OutputStreamWriter(sslsocket.getOutputStream()));
            output.println("Hello, Server!");
            output.flush();

            BufferedReader input = new BufferedReader(new InputStreamReader(sslsocket.getInputStream()));
            System.out.println(input.readLine());

            output.close();
            input.close();
            sslsocket.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}