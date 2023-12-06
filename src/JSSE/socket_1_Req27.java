import java.io.*;
import javax.net.ssl.*;

public class socket_1_Req27 {
    public static void main(String[] args) {
        try {
            // Create SSL context and initialize it with a TrustManager array
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManager[] trustManager = new TrustManager[] {
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
                }
            };
            sslContext.init(null, trustManager, new java.security.SecureRandom());

            // Get SSLSocketFactory from the SSLContext
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            // Create SSLSocket (our secure instance of a socket)
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("SERVER IP", SERVER_PORT);

            // Initialize the streams for reading/writing data from/to the server
            PrintWriter output = new PrintWriter(sslSocket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            // Write a message to the server
            output.println("Hello Server");

            // Read the response from the server
            String line = input.readLine();
            System.out.println("Server Response: " + line);

            // Close the streams and the socket
            output.close();
            input.close();
            sslSocket.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}