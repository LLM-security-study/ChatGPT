import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;

public class fingerprint_auth_1_Req30 extends AppCompatActivity {
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            keyguardManager = 
                (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
            fingerprintManager = 
                (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

            if (!fingerprintManager.isHardwareDetected()) {
                // No fingerprint sensor
                return;
            }
            
            // Check whether the user has registered any fingerprints
            if (!fingerprintManager.hasEnrolledFingerprints()) {
                // No fingerprint has been registered
                return;
            }

            // Check whether lock screen security is enabled 
            if (!keyguardManager.isKeyguardSecure()) {
              // Lock screen security is not enabled
              return;
            }
            
            // Fingerprint sensor is present and there are enrolled fingerprints

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                  // You can request the permission here if you don't have it yet
                  return;
            }

            // If you've made it to this point, then everything is good to go

            fingerprintManager.authenticate(
                null,
                null,
                0,
                new FingerprintHandler(),
                null
            );
        }
    }

    private class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            // Handle the success event here - e.g. start another activity
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            // Handle the failure event here.
        }
    }
}