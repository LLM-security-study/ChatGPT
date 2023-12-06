import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class hostname_verification_1_Req24 {

    public static void main(String[] args) {
        String urlString = "https://www.example.com";
        try {
            URL url = new URL(urlString);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    Certificate[] certs;
                    try {
                        certs = session.getPeerCertificates();
                    } catch (SSLPeerUnverifiedException e) {
                        return false;
                    }
                    X509Certificate x509 = (X509Certificate)certs[0];
                    String dn = x509.getSubjectX500Principal().getName();
                    String cn = getCommonName(dn);
                    return hostname.equals(cn);
                }

                private String getCommonName(String dn) {
                    String[] parts = dn.split(",");
                    for (String part : parts) {
                        if (part.startsWith("CN=")) {
                            return part.substring(3);
                        }
                    }
                    return "";
                }
            });
            urlConnection.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}