import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req9 {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;
        
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = null;

        try {
            socket = (SSLSocket) factory.createSocket(host, port);

            // Enable all the suites
            String[] supported = socket.getSupportedCipherSuites();
            socket.setEnabledCipherSuites(supported);

            Writer out = new OutputStreamWriter(socket.getOutputStream());
            // send a request to the server
            out.write("GET / HTTP/1.0\\r\\n");
            out.write("\\r\\n");
            out.flush();

            // receives a response from server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
