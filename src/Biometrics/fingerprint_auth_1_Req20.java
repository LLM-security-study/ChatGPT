import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class fingerprint_auth_1_Req20 extends AppCompatActivity {

    private FingerprintManager fingerprintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager = getSystemService(FingerprintManager.class);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) !=
                    PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Fingerprint authentication permission is not enabled",
                        Toast.LENGTH_LONG).show();
                return;
            }

            if (!fingerprintManager.isHardwareDetected()) {
                Toast.makeText(this, "Your device doesn't support fingerprint authentication",
                        Toast.LENGTH_LONG).show();
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                Toast.makeText(this, "No fingerprint configured. Please register at least one fingerprint",
                        Toast.LENGTH_LONG).show();
            } else {
                startAuth(fingerprintManager, null);
            }
        } else {
            Toast.makeText(this, "Fingerprint authentication is not supported in your device",
                        Toast.LENGTH_LONG).show();
        }
    }

    private void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject) {
        FingerprintManager.AuthenticationCallback authCallback = new FingerprintManager.AuthenticationCallback() {
            
            // Callback when fingerprint authentication is successful
            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                Toast.makeText(fingerprint_auth_1_Req20.this, "Authentication succeeded!", Toast.LENGTH_LONG).show();
            }

            // Callback when fingerprint authentication failed
            @Override
            public void onAuthenticationFailed() {
                Toast.makeText(fingerprint_auth_1_Req20.this, "Authentication failed", Toast.LENGTH_SHORT).show();
            }
        };

        fingerprintManager.authenticate(cryptoObject, null, 0, authCallback, null);
    }
}