import java.io.*;
import javax.net.ssl.*;

public class socket_3_Req24 {
    public static void main(String args[]) throws Exception {
        SSLContext sc = SSLContext.getDefault();
        SSLSocketFactory ssf = sc.getSocketFactory();

        //Replace "localhost" and '9999' with your server's hostname and port
        SSLSocket s = (SSLSocket) ssf.createSocket("localhost", 9999);
        
        PrintWriter writer = new PrintWriter(s.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

        //Replace this with the actual data you want to send
        writer.println("Hello, server!");
        writer.flush();

        System.out.println("Server replied: " + reader.readLine());

        writer.close();
        reader.close();
        s.close();
    }
}