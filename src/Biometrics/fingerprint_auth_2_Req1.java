import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;

public class fingerprint_auth_2_Req1 extends AppCompatActivity {
    private FingerprintManager fingerprintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager = getSystemService(FingerprintManager.class);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.USE_FINGERPRINT}, 1);
            } else if (fingerprintManager.isHardwareDetected() && fingerprintManager.hasEnrolledFingerprints()) {
                FingerprintManager.AuthenticationCallback callback = new FingerprintManager.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        // Authentication success, you can grant the user access now.
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        // Authentication failed, inform the user.
                    }
                };

                FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(null);
                fingerprintManager.authenticate(cryptoObject, null, 0, callback, null);
            }
        }
    }
}