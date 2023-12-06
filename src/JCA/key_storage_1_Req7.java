import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.KeyStore;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.security.SecureRandom;
import javax.crypto.SecretKey;

public class key_storage_1_Req7 {
 public static void main(String[] args) throws Exception {

  KeyGenerator keyGen = KeyGenerator.getInstance("AES");
  keyGen.init(128);  // 128-bit AES key
  SecretKey key = keyGen.generateKey();
  
  KeyStore keyStore = KeyStore.getInstance("JKS");
  char[] password = "changeit".toCharArray();
  keyStore.load(null, password);
  
  String alias = "mykey";
  KeyStore.SecretKeyEntry keyEntry = new KeyStore.SecretKeyEntry(key);
  KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(password);
  
  keyStore.setEntry(alias, keyEntry, protParam);
  
  // Save the keystore to a file
  try (FileOutputStream fos = new FileOutputStream("mykeystore.jks")) {
   keyStore.store(fos, password);
  }
  
  // Read the key back from the keystore
  try (FileInputStream fis = new FileInputStream("mykeystore.jks")) {
   keyStore.load(fis, password);
   Key key2 = keyStore.getKey(alias, password);
   if (key.equals(key2)) {
    System.out.println("Same key as before");
   } else {
    System.out.println("Key not found");
   }
  }
 }
}