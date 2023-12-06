import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

public class fingerprint_auth_2_Req9 {

    private FingerprintManager fingerprintManager;
    private CancellationSignal cancellationSignal;

    public void startAuth(){
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED){
            return;
        }

        if(fingerprintManager != null && fingerprintManager.isHardwareDetected()){
            if(!fingerprintManager.hasEnrolledFingerprints()){
                // User hasn't enrolled any fingerprints, so notify user.
                Toast.makeText(this, "No fingerprint configured. Please register at least one.", Toast.LENGTH_LONG).show();
                return;
            }
            
            // Begin listening for fingerprint authentication
            fingerprintManager.authenticate(null, cancellationSignal, 0, new FingerprintManager.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, CharSequence errString) {
                    Toast.makeText(fingerprint_auth_2_Req9.this, "Authentication error: " + errString, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                    Toast.makeText(fingerprint_auth_2_Req9.this, "Authentication help: " + helpString, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationFailed() {
                    Toast.makeText(fingerprint_auth_2_Req9.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                    Toast.makeText(fingerprint_auth_2_Req9.this, "Authentication successful!", Toast.LENGTH_SHORT).show();
                    // user authenticated, you can carry on the flow from here
                }
            }, null);
        }
    }
}