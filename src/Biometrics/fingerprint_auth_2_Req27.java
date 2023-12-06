import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.widget.Toast;

@SuppressLint("NewApi")
public class fingerprint_auth_2_Req27 extends AppCompatActivity {

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_BIOMETRIC)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fingerprintManager = getSystemService(FingerprintManager.class);
        keyguardManager = getSystemService(KeyguardManager.class);

        if (!fingerprintManager.isHardwareDetected()) {
            Toast.makeText(this, "Your device doesn't support fingerprint authentication", 
                           Toast.LENGTH_LONG).show();
        } else {
            if (!fingerprintManager.hasEnrolledFingerprints()) {
                Toast.makeText(this, "No fingerprint configured. Please register at least one fingerprint 
                                     in your device's settings", Toast.LENGTH_LONG).show();
            } else {
                if (!keyguardManager.isKeyguardSecure()) {
                    Toast.makeText(this, "Please enable lockscreen security in your device's settings", 
                                   Toast.LENGTH_LONG).show();
                } else {
                    startAuthentication();
                }
            }
        }
    }

    private void startAuthentication(){
        // Call the FingerprintManager's authenticate method here.
        // This is where you will generally start another activity or maybe a service 
        // which will show a dialog for the user to touch their fingerprint sensor.
    }
}