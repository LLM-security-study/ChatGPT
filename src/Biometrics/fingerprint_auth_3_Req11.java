import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyProperties;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import java.security.KeyStore;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class fingerprint_auth_3_Req11 extends AppCompatActivity {

    private KeyStore keyStore;
    private static final String KEY_NAME = "androidHive";
    private Cipher cipher;
    private FingerprintManager fingerprintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

            if (!fingerprintManager.isHardwareDetected()) {
                // Device doesn't support fingerprint authentication  
            } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                // User hasn't granted permission to use Fingerprint. Request it here.
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                // User hasn't enrolled any fingerprints to authenticate with  
            } else {
                // Everything is ready for fingerprint authentication 
            }
        }
    }
}