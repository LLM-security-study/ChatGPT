import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class fingerprint_auth_1_Req6 extends AppCompatActivity {

    private FingerprintManager fingerprintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

            if (!fingerprintManager.isHardwareDetected()) {
                Toast.makeText(this, "Fingerprint authentication permission not enabled", Toast.LENGTH_LONG).show();
            } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Allow fingerprint permission in settings", Toast.LENGTH_LONG).show();
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                Toast.makeText(this, "At least one fingerprint is necessary to run fingerprint authentication", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Fingerprint authentication is set up.", Toast.LENGTH_LONG).show();
                FingerprintHandler fingerprintHandler = new FingerprintHandler(this);
                fingerprintHandler.startAuthentication();
            }
        }
    }
}