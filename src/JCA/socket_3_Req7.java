import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req7 {
    public static void main(String[] arstring) {
        int port = YOUR_PORT;  // replace with actual port number
        String host = "YOUR_HOST"; // replace with actual host
        
        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(host, port);

            // Enable all the suites
            String[] supported = sslSocket.getSupportedCipherSuites();
            sslSocket.setEnabledCipherSuites(supported);

            Writer writer = new OutputStreamWriter(sslSocket.getOutputStream());
            writer.write("Hello Secure World!\n");
            writer.flush();

            // Reading response from the server
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }

            writer.close();
            bufferedReader.close();
            sslSocket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}