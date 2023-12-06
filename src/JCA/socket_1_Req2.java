import java.io.*;
import javax.net.ssl.*;

public class socket_1_Req2 {
    public static void main(String[] args) {
        String host = "<HostName/IP>";
        int port = <PortNumber>;

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(host, port);

            // Begin handshake
            socket.startHandshake();

            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            out.println("Hello Server");
            out.println("QUIT");
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);


            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("IOException occured: " + e.getMessage());
        }
    }
}