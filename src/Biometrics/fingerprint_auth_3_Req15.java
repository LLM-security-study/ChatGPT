import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.KeyStore;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

public class fingerprint_auth_3_Req15 extends AppCompatActivity {

    private Cipher cipher;
    private KeyStore keyStore;
    private SecretKey secretKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkFingerprintSupport();

        generateKey();

        Cipher cipher = getCipher();
        FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);

        FingerprintHandler fingerprintHandler = new FingerprintHandler(this);
        fingerprintHandler.startAuth((FingerprintManager) getSystemService(FINGERPRINT_SERVICE), cryptoObject);
    }

    protected void checkFingerprintSupport() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
            FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            if (!fingerprintManager.isHardwareDetected()) {
                Toast.makeText(this, "Your device doesn't support fingerprint authentication", Toast.LENGTH_SHORT).show();
            } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED ) {
                Toast.makeText(this, "Fingerprint authentication permission not enabled", Toast.LENGTH_SHORT).show();
            } else if (!keyguardManager.isKeyguardSecure()) {
                Toast.makeText(this, "Lock screen security not enabled in Settings", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
            keyGenerator.init(new SecureRandom());
            keyGenerator.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cipher getCipher() {
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}