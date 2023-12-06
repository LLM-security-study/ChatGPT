import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req3 {
    
    public static void main(String[] args) throws IOException {

        // Specify the Host
        String host = "yourhost.com";
        // Specify the Port 
        int port = 443;

        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
     
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
        out.println("GET / HTTP/1.0");
        out.println();
        out.flush();

        if (out.checkError())
            System.out.println("SSLSocketClient:  java.io.PrintWriter error");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);

        in.close();
        out.close();
        socket.close();

    }
}