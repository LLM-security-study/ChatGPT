import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_2_Req26 {
	public static void main(String[] args) {

		try {
			//Specify the URL here
			URL url = new URL("https://www.google.com");

			//Create a HttpsURLConnection object
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();

			//Set hostname verification
			conn.setHostnameVerifier((hostname, sslSession) -> {
				if (hostname.equals(sslSession.getPeerHost())) {
					return true;
				}
				return false;
			});

			//If the hostname verification is successful, it would not throw SSLPeerUnverifiedException
			System.out.println(conn.getCipherSuite());

		} catch (IOException e) {
			System.out.println("Error in establishing connection: " + e.getMessage());
		} catch (SSLPeerUnverifiedException ex) {
			System.out.println("Hostname verification failed: " + ex.getMessage());
		}
	}
}