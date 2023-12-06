import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStore.SecretKeyEntry;
import java.security.Key;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class key_storage_1_Req18 {
  public static void main(String[] args) {
    try{
      // Generating a secret key using KeyGenerator
      KeyGenerator keyGen = KeyGenerator.getInstance("AES");
      keyGen.init(256); 
      SecretKey secretKey = keyGen.generateKey();
      
      // Creating a KeyStore container and a SecretKeyEntry
      KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
      keyStore.load(null, null);
      SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
      
      // Set a password for the keystore and for the key entry
      char[] password = "keyStorePassword".toCharArray();
      KeyStore.ProtectionParameter protectionParameter = new KeyStore.PasswordProtection(password);
      
      // Adding the secret key to the keystore
      keyStore.setEntry("aliasSecretKey", secretKeyEntry, protectionParameter);
      
      // Saving the keystore to a file
      try (FileOutputStream fos = new FileOutputStream("keyStoreFileName")) {
          keyStore.store(fos, "keyStorePassword".toCharArray());
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}