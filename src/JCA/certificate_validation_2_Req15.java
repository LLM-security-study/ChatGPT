import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class certificate_validation_2_Req15 {
    public static void main(String[] args) throws Exception {
        // Specify the keystore details
        String keystoreType = KeyStore.getDefaultType();
        String keystoreFile = "<keystore_file_path>";
        String keystorePass = "<keystore_password>";

        // Load the keystore in the 'KeyStore' object
        KeyStore keystore = KeyStore.getInstance(keystoreType);
        try (InputStream is = certificate_validation_2_Req15.class.getResourceAsStream(keystoreFile)) {
            keystore.load(is, keystorePass.toCharArray());
        }

        // Create and initialize 'TrustManagerFactory'
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keystore);

        // Get 'TrustManagers' from 'TrustManagerFactory'
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

        // Create and initialize 'SSLContext'
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagers, null);

        // The URL of the host you will connect to
        String hostUrl = "<host_url>";

        // Create a 'HttpsURLConnection' object
        URL url = new URL(hostUrl);
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

        // Set the SSLSocketFactory
        urlConnection.setSSLSocketFactory(sslContext.getSocketFactory());

        // Connect to the host and fetch response code
        urlConnection.connect();
        int responseCode = urlConnection.getResponseCode();

        // Validate the certificate and print the response code
        if (responseCode == 200) {
            System.out.println("Certificate is valid.");
        } else {
            System.out.println("Certificate is invalid. Response Code: " + responseCode);
        }
    }
}