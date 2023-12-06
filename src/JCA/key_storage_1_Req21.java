import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

class key_storage_1_Req21 {
  public static void main(String[] args) {
    try {
      // First, we generate a secret key with DESede algorithm.
      KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
      keyGenerator.init(168);
      SecretKey secretKey = keyGenerator.generateKey();

      // Then, we create a keystore.
      KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
      
      // We load a null keystore to initialize the keystore.
      keyStore.load(null, null);

      // Now, we store the secret key inside the keystore, with an alias "mykey".
      KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
      KeyStore.ProtectionParameter protectionParam 
          = new KeyStore.PasswordProtection("password".toCharArray());
      keyStore.setEntry("mykey", secretKeyEntry, protectionParam);
      
      // Finally, we store the keystore in a file named "mykeystore.jks".
      keyStore.store(new FileOutputStream(System.getProperty("user.home") + "/mykeystore.jks"), 
          "password".toCharArray());

      System.out.println("Cryptographic key stored successfully");
    } catch (NoSuchAlgorithmException | KeyStoreException 
        | CertificateException | IOException e) {
      System.err.println("Error while storing the cryptographic key: " + e);
    }
  }
}