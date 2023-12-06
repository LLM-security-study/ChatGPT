import android.Manifest;
import android.hardware.fingerprint.FingerprintManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class fingerprint_auth_2_Req22 extends AppCompatActivity {

    private FingerprintManager fingerprintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Checking fingerprint support
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        if (!fingerprintManager.isHardwareDetected()) {
            Toast.makeText(this, "Fingerprint authentication not supported", Toast.LENGTH_LONG).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Fingerprint authentication permission not enabled", Toast.LENGTH_LONG).show();
            return;
        }

        if (!fingerprintManager.hasEnrolledFingerprints()) {
            Toast.makeText(this, "Register at least one fingerprint in Settings", Toast.LENGTH_LONG).show();
            return;
        }

        FingerprintHandler handler = new FingerprintHandler(this);
        handler.startAuth(fingerprintManager, null);
    }

}