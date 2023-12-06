import java.io.FileOutputStream;
import java.security.KeyStore;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class key_storage_2_Req8 {

    public static void main(String[] args) {

        try {
            KeyStore.PasswordProtection keyStorePP = new KeyStore.PasswordProtection("myPassword".toCharArray());

            KeyStore ks = KeyStore.getInstance("JCEKS");
            ks.load(null, "myPassword".toCharArray()); // null input stream => new (empty) keystore

            // generating key for accessToken 
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); // for example
            SecretKey secretKey = keyGen.generateKey();

            KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(secretKey);
            ks.setEntry("accessToken", skEntry, keyStorePP);

            //storing keystore to file
            try (FileOutputStream fos = new FileOutputStream("accessToken.keystore")) {
                ks.store(fos, "myPassword".toCharArray());
            }
            System.out.println("Access token stored in KeyStore file successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}