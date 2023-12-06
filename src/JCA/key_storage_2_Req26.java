import java.security.KeyStore;
import java.security.KeyStore.SecretKeyEntry;
import java.security.KeyStore.PasswordProtection;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.util.Base64;

public class key_storage_2_Req26 {
    public static void main(String[] args) throws Exception {
        String accessToken = "your_access_token";
        
        // Encode the Access Token as a Secret Key
        byte[] encodedAccessToken = Base64.getEncoder().encode(accessToken.getBytes());
        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
        secretKey = new SecretKeySpec(encodedAccessToken, 0, encodedAccessToken.length, "AES");
        
        // Loading the Keystore
        KeyStore keyStore = KeyStore.getInstance("JCEKS");
        char[] keyStorePassword = "your_keystore_password".toCharArray();
        keyStore.load(null, keyStorePassword); 
        
        // Storing Access Token in Keystore
        PasswordProtection keyPassword = new PasswordProtection(keyStorePassword);
        SecretKeyEntry secretKeyEntry = new SecretKeyEntry(secretKey);
        keyStore.setEntry("accessToken", secretKeyEntry, keyPassword);

        // Saving the Keystore on Disk
        try (FileOutputStream fos = new FileOutputStream("my.keystore")) {
            keyStore.store(fos, keyStorePassword);
        } 
    }
}