import javax.net.ssl.*;
import java.io.IOException;
import java.net.Socket;

public class hostname_verification_2_Req30 {

    public static void main(String[] args) {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {}
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {}
                public java.security.cert.X509Certificate[] getAcceptedIssuers() { return new java.security.cert.X509Certificate[]{}; }
            }}, new java.security.SecureRandom());

            SSLSocketFactory factory = sslContext.getSocketFactory();

            // Example of hostname and port
            SSLSocket socket = (SSLSocket) factory.createSocket("www.example.com", 443);

            socket.startHandshake();

            SSLSession session = socket.getSession();
            HostnameVerifier hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
            if (!hostnameVerifier.verify("www.example.com", session)) {
                throw new SSLHandshakeException("Expected 'www.example.com', but find " + session.getPeerPrincipal());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}