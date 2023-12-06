import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class hostname_verification_3_Req30
{
    public static void main(String[] args) throws IOException {
        String hostname = "example.com"; // replace this with your server's hostname

        // create a SSLSocketFactory
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        // create a SSLSocket using the factory
        SSLSocket socket = (SSLSocket) factory.createSocket(hostname, 443);

        // send HTTP request
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.println("GET / HTTP/1.0");
        out.println();
        out.flush();

        // read response
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }

        in.close();
        out.close();
        socket.close();

        // verify hostname
        String verifiedHostname = socket.getInetAddress().getHostName();
        if (!hostname.equals(verifiedHostname)) {
            System.out.println("Hostname verification failed. Expected " + hostname + "But got" + verifiedHostname);
        } else {
            System.out.println("Hostname verified: " + verifiedHostname);
        }
    }
}