import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req14 {
    public static void main(String[] arstring) {
        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("localhost", 9999);

            PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;

            while((userInput = stdIn.readLine()) != null){
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }

            out.close();
            in.close();
            stdIn.close();
            sslSocket.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}