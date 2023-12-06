import java.security.KeyStore;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.KeyGenerator;
import java.util.Base64;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class key_storage_2_Req23 {

   public static void main(String[] args) throws Exception {
      // creating password array
      char[] password = "keystorepass".toCharArray();
      // creating a new key store
      KeyStore keyStore = KeyStore.getInstance("JCEKS");

      // loading the keystore
      keyStore.load(null, password);
      
      Scanner input = new Scanner(System.in);
      System.out.println("Please enter your access token: ");

      String accessToken = input.next();
      // Converting access token to byte array
      byte[] accessTokenByte = accessToken.getBytes(StandardCharsets.UTF_8);

      SecretKey mySecretKey = new SecretKeySpec(accessTokenByte, 0, accessTokenByte.length, "AES");
      KeyStore.SecretKeyEntry skEntry=new KeyStore.SecretKeyEntry(mySecretKey);
      keyStore.setEntry("mySecretKeyAlias", skEntry, new KeyStore.PasswordProtection(password));

      // Storing the keystore
      java.io.FileOutputStream fos = null;
      try {
          fos = new java.io.FileOutputStream("newKeyStoreFileName.jceks");
          keyStore.store(fos, password);
      } finally {
          if (fos != null) {
              fos.close();
          }
      }        
   }
}
