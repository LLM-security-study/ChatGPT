import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

public class fingerprint_auth_2_Req6 extends AppCompatActivity {

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keyguardManager = getSystemService(KeyguardManager.class);
        fingerprintManager = getSystemService(FingerprintManager.class);

        if (!fingerprintManager.isHardwareDetected()) {
            // Inform the user that this device does not have a fingerprint sensor
        } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            // Inform the user that fingerprint authentication permission is not granted
        } else if (!keyguardManager.isKeyguardSecure()) {
           // Inform the user that the device is not secured with a lock screen password
        } else {
            // Inform the user that you are ready to use the fingerprint sensor
            startListeningForFingerprints();
        }
    }

    private void startListeningForFingerprints() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Inform the user that biometric permission is not granted.
            return;
        }
        
        fingerprintManager.authenticate(null, null, 0, new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                // TODO: Handle the error.
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                // TODO: Handle the success case. The user's fingerprint was recognized.
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                super.onAuthenticationHelp(helpCode, helpString);
                // TODO: Handle the help code. The user is having trouble and may need help navigating.
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                // TODO: Inform the user that their fingerprint was not recognized.
            }
        }, null);
    }
}