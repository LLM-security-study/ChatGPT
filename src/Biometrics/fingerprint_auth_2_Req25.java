import android.Manifest;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyStore;

@TargetApi(Build.VERSION_CODES.M)
public class fingerprint_auth_2_Req25 extends AppCompatActivity {

    private KeyStore keyStore;
    private static final String KEY_NAME = "yourKey";
    private Cipher cipher;
    private FingerprintManager.CryptoObject cryptoObject;
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        
        if (!fingerprintManager.isHardwareDetected()) {
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (!fingerprintManager.hasEnrolledFingerprints()) {
            return;
        }

        if (!keyguardManager.isKeyguardSecure()) {
            return;
        }

        KeyGenerator keyGenerator;
        
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) { � }
      
        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (Exception e) { � }
        
        try {
            keyStore.load(null);
            keyGenerator.init(
            new KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (Exception e) { � }
      
        if (cipherInit()) {
            cryptoObject = new FingerprintManager.CryptoObject(cipher);
            FingerprintHandler helper = new FingerprintHandler(this);
            helper.startAuthentication(fingerprintManager, cryptoObject);
        }
    }
  
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC + "/"
                    + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (Exception e) { � }
  
        try {
          keyStore.load(null);
          SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME, null);
          cipher.init(Cipher.ENCRYPT_MODE, key);
          return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (Exception e) { � }
        return false;
    }
}