import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_2_Req25 {
  private static String alias = "accessTokenAlias";
  private static char[] keyStorePassword = "secretpassword".toCharArray();
  private static char[] keyPassword = "keypassword".toCharArray();

  private static String accessToken = "1234567890abcdef";

  public static void main(String[] args) throws Exception {

    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
    CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

    // Load the keystore
    FileInputStream keyStoreData = new FileInputStream("keystore.jks");
    keyStore.load(keyStoreData, keyStorePassword);
    keyStoreData.close();

    // Store Access Token
    KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(
            new javax.crypto.spec.SecretKeySpec(accessToken.getBytes(), "RAW"));
    KeyStore.ProtectionParameter entryPassword =
            new KeyStore.PasswordProtection(keyPassword);
    keyStore.setEntry(alias, secretKeyEntry, entryPassword);

    // Save the keystore
    FileOutputStream keyStoreOutputStream = new FileOutputStream("keystore.jks");
    keyStore.store(keyStoreOutputStream, keyStorePassword);
    keyStoreOutputStream.close();
  }
}