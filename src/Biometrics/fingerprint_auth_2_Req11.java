import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

// Import other necessary packages here

public class fingerprint_auth_2_Req11 extends AppCompatActivity {

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Check if the device is running SDK >= 23 (Marshmallow)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if (!fingerprintManager.isHardwareDetected()) {
                Toast.makeText(this, "Fingerprint scanner not detected in device", Toast.LENGTH_LONG).show();
            } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission not granted to use Fingerprint Scanner", Toast.LENGTH_LONG).show();
            } else if (!keyguardManager.isKeyguardSecure()) {
                Toast.makeText(this, "Add lock to your phone in Settings", Toast.LENGTH_LONG).show();
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                Toast.makeText(this, "You should add at least 1 fingerprint to use this feature", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "You can use the fingerprint sensor", Toast.LENGTH_LONG).show();
                // Put actions you want to perform
            }
        }
    }
}