import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_2_Req2 {
    public static void main(String[] args) {
        try {
            SSLSocketFactory factory =
                    (SSLSocketFactory)SSLSocketFactory.getDefault();
            SSLSocket sslSocket = 
                    (SSLSocket)factory.createSocket("YourRemoteServer.com", 1234);

            // Enable all the suites
            String[] supported = sslSocket.getSupportedCipherSuites();
            sslSocket.setEnabledCipherSuites(supported);

            Writer out = 
                    new OutputStreamWriter(sslSocket.getOutputStream());
            out.write("GET HTTP/1.0\r\n");
            out.write("\r\n");
            out.flush();

            // Read and print the response
            BufferedReader in = 
                    new BufferedReader(
                            new InputStreamReader(sslSocket.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            out.close();
            in.close();
            sslSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}