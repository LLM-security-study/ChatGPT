import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_1_Req26 {
    public static void main(String[] args) {
        try {
            SSLSocketFactory ssf = (SSLSocketFactory)SSLSocketFactory.getDefault();

            try (SSLSocket s = (SSLSocket)ssf.createSocket("your-server-name", 443)) {
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                out.println("Hello Server");

                BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                System.out.println(input.readLine());
            }
        } catch (IOException e) {
            System.out.println("Problem occurred while setting up SSL communication: " + e.getMessage());
            e.printStackTrace();
        }
    }
}