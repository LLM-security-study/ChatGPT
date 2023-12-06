public class key_storage_3_Req12 {
    public static final String KEYSTORE_FILE = "keystore.jks"; // Path to your keystore file
    public static final String KEYSTORE_PASSWORD = "password123"; // Keystore password
    public static final String CERTIFICATE_FILE = "certificate.crt"; // Path to the certificate file
    public static final String CERTIFICATE_ALIAS = "my certificate"; // Alias of the added certificate

    public static void main(String[] args) {
        try {
            // Load keystore
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

            // If the keystore file exists, load it. If not, create a new keystore
            FileInputStream keyStoreFis = null;
            try {
                keyStoreFis = new FileInputStream(KEYSTORE_FILE);
                keyStore.load(keyStoreFis, KEYSTORE_PASSWORD.toCharArray());
            } finally {
                if (keyStoreFis != null) {
                    keyStoreFis.close();
                }
            }
    
            // Load certificate
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream certIn = new FileInputStream(CERTIFICATE_FILE);
            Certificate certificate = cf.generateCertificate(certIn);
            certIn.close();
    
            // Add the certificate
            keyStore.setCertificateEntry(CERTIFICATE_ALIAS, certificate);
    
            // Save the keystore
            try (FileOutputStream fos = new FileOutputStream(KEYSTORE_FILE)) {
                keyStore.store(fos, KEYSTORE_PASSWORD.toCharArray());
            }
    
            System.out.println("Certificate added to keystore successfully");
        } catch (Exception e) {
            System.out.println("Failed to add the certificate to keystore");
            e.printStackTrace();
        }
    }
}