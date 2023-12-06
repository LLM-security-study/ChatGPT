import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req5 {
    public static void main(String[] args) {
        try {
            //get SSL Socket Factory
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            //create SSLSocket using the factory
            SSLSocket sslSocket = (SSLSocket) factory.createSocket("localhost", 1234);

            //initialize input stream
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            //initialize output stream
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(sslSocket.getOutputStream())));

            //send a message to the server
            out.println("Hello Server!");
            out.flush();

            // print server response
            String str = in.readLine();
            System.out.println("Server response: " + str);

            // close the streams and the socket
            in.close();
            out.close();
            sslSocket.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}