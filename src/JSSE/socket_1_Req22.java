import java.io.*;
import javax.net.ssl.*;

public class socket_1_Req22 {
    public static void main(String[] args) {
        
        String hostname = "HOSTNAME";
        int port = PORT;
        SSLSocketFactory factory;

        // Keystores and TrustStores are used in JSSE for key management
        System.setProperty("javax.net.ssl.keyStore", "FILEPATH_TO_KEYSTORE");
        System.setProperty("javax.net.ssl.keyStorePassword", "KEYSTORE_PASSWORD");
        System.setProperty("javax.net.ssl.trustStore", "FILEPATH_TO_TRUSTSTORE");
        System.setProperty("javax.net.ssl.trustStorePassword", "TRUSTSTORE_PASSWORD");

        try {

            factory=(SSLSocketFactory)SSLSocketFactory.getDefault();
            SSLSocket socket=(SSLSocket)factory.createSocket(hostname, port);

            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.println("GET / HTTP/1.0");
            out.println();
            out.flush();
          
            if (out.checkError()) System.out.println("SSLSocketClient:  java.io.PrintWriter error");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);

            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}