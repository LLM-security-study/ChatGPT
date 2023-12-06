import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class fingerprint_auth_1_Req8 extends AppCompatActivity {

    private FingerprintManager fingerprintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // check support for fingerprint in device's hardware and software
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            Toast.makeText(this, "Fingerprint authentication is not supported in your device", Toast.LENGTH_LONG).show();
            return;
        }
        
        // initialized FingerprintManager
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Fingerprint authentication permission not enabled", Toast.LENGTH_LONG).show();
            return;
        }

        if (!fingerprintManager.hasEnrolledFingerprints()) {
            Toast.makeText(this, "Register at least one fingerprint in Settings", Toast.LENGTH_LONG).show();
            return;
        }
        
        //Begin authentication process
        beginAuthWithFingerprintManager();
    }

    private void beginAuthWithFingerprintManager() {
        // handle the rest authentication logic here
    }
}