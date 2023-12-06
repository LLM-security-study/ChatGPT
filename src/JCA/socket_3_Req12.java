import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req12 {
    public static void main(String[] args) {
        String host = "your.server.com";
        int port = 443; // Replace with your port

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(host, port);

            // Enable all the suites
            String[] supported = sslSocket.getSupportedCipherSuites();
            sslSocket.setEnabledCipherSuites(supported);

            Writer out = new OutputStreamWriter(sslSocket.getOutputStream());
            // Write data to the socket
            out.write("Hello Server");
            out.flush();

            // Reading response from the server
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream())); 
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("Server response: " + line);
            }

            // Make sure to close the socket
            sslSocket.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}