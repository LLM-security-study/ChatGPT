import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_2_Req11 {
    public static void main(String[] args) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket = (SSLSocket) factory.createSocket("Server IP address here", port number here);

            // This stream can be used to send data to server
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(sslsocket.getOutputStream()));

            // This stream can be used to get data from server
            BufferedReader br = new BufferedReader(new InputStreamReader(sslsocket.getInputStream()));

            // send data to server
            pw.write("Data to send here");
            pw.flush();

            // receive data from server
            String receivedData = br.readLine();

            System.out.println("Received data from Server: " + receivedData);

            // close IO and Socket
            pw.close();
            br.close();
            sslsocket.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}