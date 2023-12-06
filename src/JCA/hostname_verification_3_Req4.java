import javax.net.ssl.*;

public class hostname_verification_3_Req4 {
    public static void main(String[] args) {
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

        SSLSocket socket = null;
        try {
            socket = (SSLSocket) factory.createSocket("www.example.com", 443);
    
            SSLSession session = socket.getSession();
            String hostname = session.getPeerHost();
            System.out.println("Connected to: " + hostname);
    
            if (!"www.example.com".equals(hostname)) {
                System.out.println("Host name verification failed!");
                System.exit(1);
            }
    
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}