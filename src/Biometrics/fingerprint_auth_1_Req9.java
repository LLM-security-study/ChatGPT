import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class fingerprint_auth_1_Req9 extends AppCompatActivity {

    private FingerprintManager fingerprintManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            // Ask user for fingerprint authentication permission.
            requestPermissions(new String[]{Manifest.permission.USE_FINGERPRINT}, 1); // handle permission in "onRequestPermissionsResult" method
        } else {
            initFingerprintAuthentication();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initFingerprintAuthentication() {
        if (!fingerprintManager.isHardwareDetected()) {
            // Device doesn't support fingerprint authentication
            return;
        }

        if (!fingerprintManager.hasEnrolledFingerprints()) {
            // User hasn't enrolled any fingerprints to authenticate with
            return;
        }

        FingerprintManager.AuthenticationCallback authenticationCallback = new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                // There was an irrecoverable error and the operation was aborted.
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                // The operation was unable to start due to some recoverable error.
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                // Authentication was successful
            }

            @Override
            public void onAuthenticationFailed() {
                // The operation failed because the fingerprint that was provided is not valid.
            }
        };

        fingerprintManager.authenticate(null /* crypto object */, null /* cancellation signal */, 0 /* flags */, authenticationCallback, null /* handler */);
    }
}