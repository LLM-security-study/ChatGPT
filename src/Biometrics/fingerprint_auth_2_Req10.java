import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class fingerprint_auth_2_Req10 extends AppCompatActivity {

    private FingerprintManager fingerprintManager;
    private CancellationSignal cancellationSignal;
    private final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) 
	!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.USE_FINGERPRINT}, REQUEST_CODE);
            return;
        }

        if (!fingerprintManager.hasEnrolledFingerprints()) {
            Toast.makeText(this, "No fingerprint configured. Please register at least one.", Toast.LENGTH_LONG).show();
            return;
        }
        
        authenticateUser();
    }

    private void authenticateUser() {
        cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(null, cancellationSignal, 0, new FingerprintManager.AuthenticationCallback() {

            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                Toast.makeText(fingerprint_auth_2_Req10.this, "Authentication error: " + errString, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                Toast.makeText(fingerprint_auth_2_Req10.this, "Authentication help: " + helpString, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAuthenticationFailed() {
                Toast.makeText(fingerprint_auth_2_Req10.this, "Authentication failed.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                Toast.makeText(fingerprint_auth_2_Req10.this, "Authentication successful.", Toast.LENGTH_LONG).show();
                // you can call another activity here or do whatever you want
            }
        }, null);
    }
}