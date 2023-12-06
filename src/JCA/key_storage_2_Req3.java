import java.security.KeyStore;
import java.io.FileOutputStream;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;
import javax.crypto.spec.SecretKeySpec;

public class key_storage_2_Req3 {
  public static void main(String[] args) throws Exception {

    // Define the secret key (our access token)
    byte[] secret = "access_token".getBytes(); // insert your access token here.

    // Define the alias for the secret key
    String alias = "alias"; 

    // Define the password for the keystore
    char[] keystorePassword = "keystorePassword".toCharArray(); // keystore password

    // Define the password for the key
    char[] keyPassword = "keyPassword".toCharArray(); // key password

    // Create a new KeyStore instance
    KeyStore ks = KeyStore.getInstance("JCEKS"); 

    // Load the keystore
    ks.load(null, keystorePassword);

    // Create the secret key specification
    SecretKeySpec sks = new SecretKeySpec(secret, "AES");

    // Create the key's entry for the keystore
    SecretKeyEntry ske = new KeyStore.SecretKeyEntry(sks);

    // Set the key entry in the keystore
    ks.setEntry(alias, ske, new PasswordProtection(keyPassword));

    // Save the keystore to a file named "keystore.jceks" in your current working directory
    try (FileOutputStream fos = new FileOutputStream("keystore.jceks")) {
      ks.store(fos, keystorePassword);
    }
  }
}