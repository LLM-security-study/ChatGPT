import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.CancellationSignal;

public class fingerprint_auth_1_Req24 {
    private FingerprintManager fingerprintManager;
    private CancellationSignal cancellationSignal;

    public void checkFingerprintSupport() {
        // Check if device supports fingerprint sensing
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_FINGERPRINT))
            return;
        // Check if permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // Check if device has fingerprint sensor
        if (!fingerprintManager.isHardwareDetected()) {
            return;
        }
        // Check if device has fingerprint registered
        if (!fingerprintManager.hasEnrolledFingerprints()) {
            return;
        }
    }

    public void startAuth() {
        // Start authentication process
        cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(null, cancellationSignal, 0, new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                // Successful authentication. Write your own logic here.
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                // Authentication failed. Write your own logic here.
            }
        }, null);
    }
}