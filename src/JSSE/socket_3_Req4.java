import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req4 {

    public static void main(String[] args) {
        String hostname = "hostname"; // put the hostname you want to connect to
        int port = 443; // put the port number

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(hostname, port);

            // Enable all the suites
            String[] supported = sslSocket.getSupportedCipherSuites();
            sslSocket.setEnabledCipherSuites(supported);
            
            // Establish the connection
            OutputStream out = sslSocket.getOutputStream();
            out.write("\nSSL Connection Established!".getBytes());
            out.flush();
            
            // Reading data
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                System.out.println("Received data: " + string);
            }

            // Close the socket
            out.close();
            bufferedReader.close();
            sslSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}