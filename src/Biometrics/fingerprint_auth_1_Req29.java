import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

public class fingerprint_auth_1_Req29 {

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    public fingerprint_auth_1_Req29() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager = getApplicationContext().getSystemService(FingerprintManager.class);
            keyguardManager = getApplicationContext().getSystemService(KeyguardManager.class);

            checkFingerprintCapabilities();
        }
    }

    private void checkFingerprintCapabilities() {
        // Require at least Android 6.0 (M)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Check if the device has a fingerprint sensor
            if (!fingerprintManager.isHardwareDetected()) {
                // This device doesn't support fingerprint authentication.
                return;
            }

            // Check if the user has granted your app the USE_FINGERPRINT permission
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                // The user hasn't granted the USE_FINGERPRINT permission
                return;
            }

            // Check if the user has registered at least one fingerprint
            if (!fingerprintManager.hasEnrolledFingerprints()) {
                // The user hasn't any fingerprint registered
                return;
            }

            // The user can use fingerprint to authenticate
            authenticateUser();
        }
    }

    private void authenticateUser() {
        FingerprintManager.AuthenticationCallback authenticationCallback = new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                // Authentication error, errString to log the error.
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                // Authentication success
            }

            @Override
            public void onAuthenticationFailed() {
                // Authentication failed
            }
        };

        fingerprintManager.authenticate(null, null, 0, authenticationCallback, null);
    }
}