import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.CancellationSignal;

public class fingerprint_auth_2_Req18 extends AppCompatActivity {

    private TextView paraLabel;
    private FingerprintManager fingerprintManager;
    private CancellationSignal cancellationSignal;
    private static final int BATTERY_PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paraLabel = (TextView) findViewById(R.id.paraLabel);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

            if(!fingerprintManager.isHardwareDetected()) {
                paraLabel.setText("Fingerprint Scanner not detected in Device");
            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.USE_BIOMETRIC}, BATTERY_PERMISSION_REQUEST_CODE);
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                paraLabel.setText("Register at least one fingerprint in Settings");
            } else {
                paraLabel.setText("Place your Finger on the scanner to access the app.");

                FingerprintHandler fingerprintHandler = new FingerprintHandler(this);
                fingerprintHandler.startAuth(fingerprintManager, null);
            }
        }

    }

    private class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

        FingerprintHandler(fingerprint_auth_2_Req18 main) {
            // Callback for FingerprintManager
        }

        public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
            cancellationSignal = new CancellationSignal();
            manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
        }

        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            paraLabel.setText(errString);
        }

        @Override
        public void onAuthenticationFailed() {
            paraLabel.setText("Authentication failed");
        }

        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            paraLabel.setText(helpString);
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            paraLabel.setText("Success! You can now access the app.");
        }
    }
}