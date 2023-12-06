import java.security.KeyStore;
import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.Key;
import javax.crypto.KeyGenerator;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.KeyStore.SecretKeyEntry;
import java.io.FileOutputStream;

public class key_storage_1_Req6 {
  
  public static void main(String[] args) {
    try {
      // Generate the key
      KeyGenerator keyGen = KeyGenerator.getInstance("AES");
      keyGen.init(256);
      Key key = keyGen.generateKey();
      
      // Create the keystore
      KeyStore keyStore = KeyStore.getInstance("JCEKS");
      keyStore.load(null, null);
      
      // Set the entry
      KeyStore.SecretKeyEntry secretKeyEntry = new SecretKeyEntry(key);
      KeyStore.ProtectionParameter entryPassword = 
          new KeyStore.PasswordProtection("entry_password".toCharArray());
      keyStore.setEntry("my_secret_key", secretKeyEntry, entryPassword);
      
      // Save the keystore to a file
      FileOutputStream fos = new FileOutputStream("my_keystore.ks");
      keyStore.store(fos, "keystore_password".toCharArray());

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}