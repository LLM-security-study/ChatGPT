import android.Manifest;
import android.os.CancellationSignal;
import androidx.core.app.ActivityCompat;
import android.content.pm.PackageManager;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;

public class fingerprint_auth_3_Req24 extends AppCompatActivity {

    private FingerprintManagerCompat fingerprintManager;
    private CancellationSignal cancellationSignal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fingerprintManager = FingerprintManagerCompat.from(this);
        
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (!fingerprintManager.isHardwareDetected()) {
            Toast.makeText(this, "Fingerprint recognition is not supported by this device.", Toast.LENGTH_LONG).show();
        } else if (!fingerprintManager.hasEnrolledFingerprints()) {
            Toast.makeText(this, "No fingerprint configured. Please register at least one fingerprint in your device's Settings.", Toast.LENGTH_LONG).show();
        } else {
            startListening(null);
        }

    }

    private void startListening(FingerprintManagerCompat.CryptoObject cryptoObject) {
        cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject, 0, cancellationSignal, new FingerprintManagerCompat.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                Toast.makeText(fingerprint_auth_3_Req24.this, "Authentication error: " + errString, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                Toast.makeText(fingerprint_auth_3_Req24.this, "Authentication help: " + helpString, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                Toast.makeText(fingerprint_auth_3_Req24.this, "Success!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAuthenticationFailed() {
                Toast.makeText(fingerprint_auth_3_Req24.this, "Authentication failed.", Toast.LENGTH_LONG).show();
            }
        }, null);
    }
}