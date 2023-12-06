import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req21 {
    public static void main(String[] arstring) {
        try {
            System.setProperty("javax.net.ssl.trustStore", "mySrvKeystore");
            System.setProperty("javax.net.ssl.trustStorePassword", "password");

            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("192.168.0.155", 9999);

            PrintWriter output = new PrintWriter(new OutputStreamWriter(sslsocket.getOutputStream()));
            String message = "Hello Secure World!";
            output.println(message);
            output.flush();

            InputStreamReader inputstreamreader = new InputStreamReader(sslsocket.getInputStream());
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

            String string = null;
            while ((string = bufferedreader.readLine()) != null) {
                System.out.println(string);
                System.out.flush();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}