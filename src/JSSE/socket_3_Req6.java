import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req6{
    public static void main(String[] args) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();

            SSLSocket sslSocket = (SSLSocket)factory.createSocket("yourHostName", yourPortNumber);

            PrintWriter output = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));

            BufferedReader input = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            String request = "yourRequest";
            output.println(request);
            output.println();
            output.flush();

            String responseLine;
            while ((responseLine = input.readLine()) != null) {
                System.out.println(responseLine);
            }

            output.close();
            input.close();
            sslSocket.close();
        }
        catch (Exception e) {
            System.out.println("Exception caught "+e);
        }
    }
}