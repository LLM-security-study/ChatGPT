import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class fingerprint_auth_3_Req18 extends AppCompatActivity {
    private FingerprintManager fingerprintManager;
    private CancellationSignal cancellationSignal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (!fingerprintManager.isHardwareDetected()) {
            Toast.makeText(this, "Fingerprint authentication not supported!", Toast.LENGTH_LONG).show();
        } else if (!fingerprintManager.hasEnrolledFingerprints()) {
            Toast.makeText(this, "No fingerprint configured!", Toast.LENGTH_LONG).show();
        } else {
            startListening();
        }
    }

    private void startListening() {
        cancellationSignal = new CancellationSignal();

        fingerprintManager.authenticate(null, cancellationSignal, 0, new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                Toast.makeText(fingerprint_auth_3_Req18.this, "Authentication error: " + errString, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                Toast.makeText(fingerprint_auth_3_Req18.this, "Authentication help: " + helpString, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAuthenticationFailed() {
                Toast.makeText(fingerprint_auth_3_Req18.this, "Authentication failed!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                Toast.makeText(fingerprint_auth_3_Req18.this, "Authentication succeeded!", Toast.LENGTH_LONG).show();
            }
        }, null);
    }
}