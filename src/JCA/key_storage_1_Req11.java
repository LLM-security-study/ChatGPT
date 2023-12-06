// Import necessary packages
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.KeyStore.SecretKeyEntry;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator; 
import javax.crypto.SecretKey; 

import java.io.FileOutputStream; 

public class key_storage_1_Req11 {
  public static void main(String args[]) { 
    try {
      // Create a key generator
      KeyGenerator keyGen = KeyGenerator.getInstance("AES");
      keyGen.init(256);
      SecretKey secretKey = keyGen.generateKey();
      
      // Create a keystore
      KeyStore keyStore = KeyStore.getInstance("JCEKS");
      keyStore.load(null, null);
    
      // Wrap the secret key
      SecretKeyEntry secretKeyEntry = new SecretKeyEntry(secretKey);
    
      // Set the password
      char[] password = "<USE_YOUR_PASSWORD>".toCharArray();
  
      // Put the secret key into the keystore
      keyStore.setEntry("mySecretKey", secretKeyEntry, new KeyStore.PasswordProtection(password));
      
      // Write the keystore to file
      try (FileOutputStream fos = new FileOutputStream("keyStoreName.jceks")) {
        keyStore.store(fos, password);
      }
        
      System.out.println("The key was successfully stored in the keystore.");
    } catch(KeyStoreException | NoSuchAlgorithmException | java.io.IOException | java.security.cert.CertificateException e) {
      System.out.println("There was an error storing the key in the keystore: " + e.getMessage());
    }
  }
}