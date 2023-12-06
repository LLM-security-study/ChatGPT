import android.content.pm.PackageManager;
import android.Manifest;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

public class fingerprint_auth_2_Req15 {

    private FingerprintManager fingerprintManager;
    
    // Check whether the device has a Fingerprint sensor.
    private void checkFingerprintSupport() {
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            // Yes, the device has a Fingerprint sensor.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Make sure that it is Android 6.0 (Marshmallow) and above.
                fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
                if (!fingerprintManager.isHardwareDetected()) {
                    // Device doesn't support fingerprint authentication.
                } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                    // User hasn't enrolled any fingerprints.
                } else {
                    // Everything is ready for fingerprint authentication.
                    authenticateUser();
                }
            }
        } else {
            // No, the device haven't a Fingerprint sensor.
        }
    }

    public void authenticateUser() {
        // Do the authentication process.
        FingerprintManager.CryptoObject cryptoObject;
        // You should include the functionality to encrypt the data here.
        FingerprintManager.AuthenticationCallback authenticationCallback = 
            new FingerprintManager.AuthenticationCallback() {
                @Override
                public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                    // Authentication successful.
                }

                @Override
                public void onAuthenticationError(int errorCode, CharSequence errString) {
                    // Authentication error.
                }

                @Override
                public void onAuthenticationFailed() {
                    // Authentication failed.
                }
            };

        FingerprintManager.AuthenticationCallback authenticationCallback;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) 
                    != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fingerprintManager.authenticate(cryptoObject, null, 0, authenticationCallback, null);
    }
}