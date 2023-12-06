import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req1 {

    public static void main(String[] args) {
        try {
            String serverName = "YourServer";     //Replace with your Server's Name 
            int serverPort = YourPortNumber;      //Replace with your Server's Port Number

            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(serverName, serverPort);

            PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            out.println("Hello Server");
            String serverResponse = in.readLine();
            System.out.println("Server Response: " + serverResponse);

            in.close();
            out.close();
            sslSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}