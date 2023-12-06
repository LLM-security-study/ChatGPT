import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class hostname_verification_3_Req29 {
    public static void main(String[] args) {
        String server = "https://server.example.com"; //Replace with the server's URL
        try {
            verifyHostname(server);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void verifyHostname(String urlS) throws Exception {
        URL url = new URL(urlS);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setHostnameVerifier((hostname, session) -> true);
        conn.connect();

        Certificate[] certificates = conn.getServerCertificates();
        X509Certificate x509cert = (X509Certificate) certificates[0];
        String dn = x509cert.getSubjectX500Principal().getName();
        String cn = null;
        for (String str : dn.split(",")) {
            if (str.startsWith("CN")) {
                cn = str.substring(3);
                break;
            }
        }
        if (url.getHost().equalsIgnoreCase(cn)) {
            System.out.println("Server hostname matches with the hostname in certificate");
        } else {
            throw new SSLPeerUnverifiedException("Server hostname does not match the hostname in certificate");
        }
    }
}