public class certificate_validation_1_Req10 {
    public static void main(String[] args) throws Exception {
        // Load the certificate
        InputStream inStream = new FileInputStream("server.crt");
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate serverCert = cf.generateCertificate(inStream);
        inStream.close();

        // Create a KeyStore containing our trusted CAs
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        keyStore.setCertificateEntry("server", serverCert);

        // Create a TrustManager that trusts the server in our KeyStore
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(keyStore);

        // Create an SSLContext that uses our TrustManager
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, tmf.getTrustManagers(), null);

        // Create an SSLSocketFactory
        SSLSocketFactory sslSocketFactory = context.getSocketFactory();

        // Connect to the server
        SSLSocket socket = (SSLSocket) sslSocketFactory.createSocket(InetAddress.getByName("server.com"), 443);

        // Validate the obtained certificate against the serverCert
        CertPathValidator cpv = CertPathValidator.getInstance(CertPathValidator.getDefaultType());
        PKIXParameters params = new PKIXParameters(keyStore);
        params.setRevocationEnabled(false);
        CertPath cp = cf.generateCertPath(Collections.singletonList(serverCert));
        cpv.validate(cp, params);

        // Use the socket as usual...
    }
}