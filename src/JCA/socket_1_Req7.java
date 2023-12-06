import javax.net.ssl.*;
import java.io.*;

public class socket_1_Req7 {

    public static void main(String[] args) {
        int port = 8080;
        try {
            SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket s = (SSLServerSocket) ssf.createServerSocket(port);
            System.out.println("Server ready....");
            SSLSocket c = (SSLSocket) s.accept();

            Writer w = new OutputStreamWriter(c.getOutputStream());
            w.write("HTTP/1.0 200");
            w.write("\r\n");
            w.write("Content-Type: text/plain");
            w.write("\r\n");
            w.write("\r\n");
            w.write("Hello, World");
            w.flush();
            w.close();
            c.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}