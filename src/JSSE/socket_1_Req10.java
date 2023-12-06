import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

class socket_1_Req10 {

    private static final String HOST = "localhost";
    private static final int PORT = 1234;

    public static void main(String[] argv) {
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try {
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(HOST, PORT);
			
            PrintWriter out = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            
            String message = "Hello, Server!";
            out.println(message);
            out.flush();
			
            System.out.println("Message sent to server: " + message);
			
            System.out.printf("Response from server: " + in.readLine()); 
			
            // cleanup
            in.close();
            out.close();
            sslSocket.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}