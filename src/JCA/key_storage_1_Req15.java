import java.security.*;
import java.io.*;

public class key_storage_1_Req15 {
    public static void main(String[] args) throws Exception{
        // Get the password for the key store
        char[] password = "password".toCharArray();

        // Generate a new key pair
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();

        // Generate certificate.
        Certificate[] chain = {generateCertificate("alias", pair, 365, "SHA256withRSA")};

        // Store the key and the certificate chain in the key store
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null, password);
        keyStore.setKeyEntry("alias", privateKey, password, chain);

        // Store the key store
        try (FileOutputStream fos = new FileOutputStream("keystore.jks")) {
            keyStore.store(fos, password);
        }
    }
    
    private static Certificate generateCertificate(String dn, KeyPair pair, int days, String algorithm)
        throws GeneralSecurityException, IOException
    {
        // Some of this code has been omitted because it's not relevant to the original question. 
        // Generation of certificates normally involves a certificate authority, and should not be done in a simplistic manner in a live environment.
    }
}