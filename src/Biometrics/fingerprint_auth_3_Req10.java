// Remember you need to request the use_fingerprint permission in your manifest.
// Also there are developers options that allow you to test fingerprint manager in the emulator.

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

public class fingerprint_auth_3_Req10Activity extends AppCompatActivity {

    private FingerprintManager fingerprintManager;
    private CancellationSignal cancellationSignal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fingerprintManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        authenticateUser();
    }

    public void authenticateUser() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        FingerprintManager.AuthenticationCallback authenticationCallback = new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(fingerprint_auth_3_Req10Activity.this, "Authentication succeeded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(fingerprint_auth_3_Req10Activity.this, "Authentication failed!", Toast.LENGTH_SHORT).show();
            }
        };

        fingerprintManager.authenticate(null, cancellationSignal, 0, authenticationCallback, null);
    }
}