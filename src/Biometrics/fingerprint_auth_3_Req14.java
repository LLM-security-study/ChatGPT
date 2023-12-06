import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class fingerprint_auth_3_Req14 extends AppCompatActivity {

    private KeyStore keyStore;
    private static final String KEY_NAME = "fingerprintKey";
    private Cipher cipher;
    private TextView textView;
    private FingerprintManager.CryptoObject cryptoObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check whether the device has a fingerprint sensor
        FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        if (!fingerprintManager.isHardwareDetected()) {
            textView.setText("Your device doesn't support fingerprint authentication");
        } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            textView.setText("Please enable the fingerprint permission");
        } else if (!fingerprintManager.hasEnrolledFingerprints()) {
            textView.setText("No fingerprint configured. Please register at least one fingerprint in your device's Settings");
        } else {
            // The user can use the fingerprint sensor
            generateKey();

            if (cipherInit()) {
                cryptoObject = new FingerprintManager.CryptoObject(cipher);
                FingerprintHandler helper = new FingerprintHandler(this);
                helper.startAuth(fingerprintManager, cryptoObject);
            }
        }
    }

    protected void generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            keyStore.load(null);
            keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC).setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7).build());
            keyGenerator.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }

        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME, null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}