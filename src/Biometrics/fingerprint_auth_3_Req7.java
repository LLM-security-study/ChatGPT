import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class fingerprint_auth_3_Req7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing both Android Keyguard Manager and Fingerprint Manager
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        // Checking if user has granted fingerprint permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Check whether the device has a fingerprint sensor.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!fingerprintManager.isHardwareDetected()) {
                // Device doesn't support fingerprint authentication                
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                // User hasn't enrolled any fingerprints to authenticate with
            } else {
                // The user has at least one fingerprint enrolled, so you can use the fingerprint scanner.
                // FingerprintHandler is your own class that will handle the authentication process.
                FingerprintHandler helper = new FingerprintHandler(this);
                helper.startAuth(fingerprintManager, null);
            }
        }
    }
}