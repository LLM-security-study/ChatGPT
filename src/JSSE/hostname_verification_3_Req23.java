import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class hostname_verification_3_Req23 {

    public static void main(String[] args) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket("yourServerHostname", 443);

            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.println("GET / HTTP/1.0");
            out.println();
            out.flush();

            if (out.checkError())
                System.out.println("SSLSocketClient:  java.io.PrintWriter error");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

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