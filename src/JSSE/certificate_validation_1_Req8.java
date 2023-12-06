import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class certificate_validation_1_Req8 {
    public static void main(String[] args) throws Exception {
        //Create an SSLSocketFactory
        SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        //Create an SSLSocket indicating the SSL server host, port
        SSLSocket sslSocket = (SSLSocket) ssf.createSocket("ServerIPAdress", 443);

        //Initialize the streams for communication with the server
        PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

        SSLSession session = sslSocket.getSession();

        //Printing SSLSession info:
        System.out.println("PeerHost = " + session.getPeerHost());
        System.out.println("Cipher suite = " + session.getCipherSuite());

        //Sending message to server
        out.println("Hello Server!");

        //Checking server response
        System.out.println("Server said: " + in.readLine());

        //closing the streams and the socket
        out.close();
        in.close();
        sslSocket.close();
    }
}