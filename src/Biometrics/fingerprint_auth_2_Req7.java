import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.hardware.fingerprint.FingerprintManager;
import android.widget.TextView;
import android.widget.Toast;

public class fingerprint_auth_2_Req7 extends AppCompatActivity {

    private FingerprintManager fingerprintManager;
    private FingerprintManager.AuthenticationCallback authenticationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check for necessary permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(fingerprint_auth_2_Req7.this, "Fingerprint authentication permission not enabled", Toast.LENGTH_LONG).show();
            return;
        }

        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
 
        if (!fingerprintManager.isHardwareDetected()) {
            Toast.makeText(fingerprint_auth_2_Req7.this, "Your device does not have a Fingerprint Sensor", Toast.LENGTH_LONG).show();
            return;
        }

        if (!fingerprintManager.hasEnrolledFingerprints()) {
            Toast.makeText(fingerprint_auth_2_Req7.this, "No fingerprint configured. Please register at least one fingerprint in your device's Settings", Toast.LENGTH_LONG).show();
            return;
        }

        authenticationCallback = new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                Toast.makeText(fingerprint_auth_2_Req7.this, "Authentication error\n" + errString, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                Toast.makeText(fingerprint_auth_2_Req7.this, "Authentication help\n" + helpString, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                Toast.makeText(fingerprint_auth_2_Req7.this, "Success!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAuthenticationFailed() {
                Toast.makeText(fingerprint_auth_2_Req7.this, "Authentication failed", Toast.LENGTH_LONG).show();
            }
        };

        FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);

        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, authenticationCallback, null);
    }
}