import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;
import java.net.URL;

public class hostname_verification_2_Req1 {

    public static void main(String[] args) {

        String host = "https://the_hostname_to_verify";
        try {
            URL url = new URL(host);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession sslSession) {
                    // go through your own hostname verification process
                    // The most straightforward verification could be:
                    if (hostname.equals(sslSession.getPeerHost())) {
                        System.out.println("Hostname Verification Passed");
                        return true;
                    } else {
                        System.out.println("Hostname Verification Failed");
                        return false;
                    }
                }
            });

            httpsURLConnection.connect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}