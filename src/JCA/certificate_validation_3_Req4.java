import javax.net.ssl.*;

public class certificate_validation_3_Req4 {

    public static void main(String[] args) {
		try {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					public void checkClientTrusted(
						java.security.cert.X509Certificate[] certs, String authType) {
					}

					public void checkServerTrusted(
						java.security.cert.X509Certificate[] certs, String authType) {
					}
				}
			};

			// Install the all-trusting trust manager
			SSLContext sC = SSLContext.getInstance("SSL"); //I'm using SSL here, for TLS replace it with "TLS"
			sC.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sC.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true; // change this to a real host verification
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

			//Now you can access an https URL without having the certificate in the truststore

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}