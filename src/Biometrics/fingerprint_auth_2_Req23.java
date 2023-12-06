import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

@RequiresApi(api = Build.VERSION_CODES.M)
public class fingerprint_auth_2_Req23 extends AppCompatActivity {

    private static final String KEY_NAME = "FingerprintKey";
    private Cipher cipher;
    private KeyStore keyStore;
    private FingerprintManager fingerprintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if the device has a fingerprint sensor
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        if (!fingerprintManager.isHardwareDetected()) {
            Toast.makeText(this, "Fingerprint authentication is not supported on this device.", Toast.LENGTH_LONG).show();
            return;
        }

        // Check if the user has granted your app the USE_FINGERPRINT permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Fingerprint authentication permission is not enabled.", Toast.LENGTH_LONG).show();
            return;
        }

        // Check if the user has registered at least one fingerprint on the device
        if (!fingerprintManager.hasEnrolledFingerprints()) {
            Toast.makeText(this, "Register at least one fingerprint in Settings", Toast.LENGTH_LONG).show();
            return;
        }

        generateKey();
        
        if (cipherInit()) {
            FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
            FingerprintHandler helper = new FingerprintHandler(this);
            helper.startAuth(fingerprintManager, cryptoObject);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected void generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
            keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keyStore.getKey(KEY_NAME, null));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}