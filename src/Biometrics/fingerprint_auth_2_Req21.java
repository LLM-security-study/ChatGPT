import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import android.widget.Toast;
import java.security.KeyStore;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

@RequiresApi(api = Build.VERSION_CODES.M)
public class fingerprint_auth_2_Req21 {

    private static final String KEY_NAME = "your_key_name";
    private KeyStore keyStore;
    private Cipher cipher;

    public void startFingerprintAuthentication(FingerprintManager fingerprintManager) {

        KeyGenerator keyGenerator;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != 
        PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (!fingerprintManager.isHardwareDetected()) {
            Toast.makeText(this, "Fingerprint hardware not detected", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!fingerprintManager.hasEnrolledFingerprints()) {
            Toast.makeText(this, "No fingerprint configured", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (Exception e) {
            throw new RuntimeException("Failed to get KeyGenerator instance", e);
        }

        try {
            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (initCipher(cipher, KEY_NAME)) {
            FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
            FingerprintHandler helper = new FingerprintHandler(this);
            helper.startAuth(fingerprintManager, cryptoObject);
        }
    }

    public boolean initCipher(Cipher cipher, String keyName) {
        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(keyName,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }
}