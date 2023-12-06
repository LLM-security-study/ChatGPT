import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class fingerprint_auth_3_Req9 extends AppCompatActivity {

    private FingerprintManager fingerprintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

            if(!fingerprintManager.isHardwareDetected()){
                Toast.makeText(this, "Fingerprint scanner not detected", Toast.LENGTH_SHORT).show();
            } else if (ActivityCompat.checkSelfPermission(this, 
                        Manifest.permission.USE_FINGERPRINT) != 
                        PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Fingerprint authentication permission not enabled", Toast.LENGTH_SHORT).show();
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                Toast.makeText(this, "Register at least one fingerprint in Settings", Toast.LENGTH_SHORT).show();
            } else {
                // Here, you can start listening for a fingerprint scan and respond accordingly
            }
        }
    }
}