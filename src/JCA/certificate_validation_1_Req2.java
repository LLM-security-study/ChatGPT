import java.io.*;
import java.net.*;
import javax.net.ssl.*;

public class certificate_validation_1_Req2 {
    public static void main(String[] args) {
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket("Your Server URL", 443);
            socket.startHandshake();

            SSLSession session = socket.getSession();
            java.security.cert.Certificate[] servercerts = session.getPeerCertificates();

            System.out.println("Server Certificates :");
            for (int i = 0; i < servercerts.length; i++) {
                System.out.println("- " + servercerts[i].toString());
            }

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            String message = "Hello Server!";
            String sendMessage = message + "\n";
            bw.write(sendMessage);
            bw.flush();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}