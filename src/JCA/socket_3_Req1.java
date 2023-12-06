import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req1 {
    public static void main(String[] args) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket("localhost", 443);

            PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            output.println("HELLO SERVER");
            output.println();
            output.flush();

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = input.readLine();

            System.out.println("Server response: " + response);

            socket.close();
        } 
        catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }
}