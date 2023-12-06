import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;

public class fingerprint_auth_2_Req16 extends AppCompatActivity {

    private TextView textView;
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if the device is running on Android 6.0 (Marshmallow) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Get an instance of FingerprintManager and KeyguardManager
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            // Check if the device has a fingerprint sensor
            if (!fingerprintManager.isHardwareDetected()) {
                textView.setText("Your device doesn't support fingerprint authentication");
            } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                textView.setText("Please enable the fingerprint permission");
            } else if (!keyguardManager.isKeyguardSecure()) {
                textView.setText("Add lock to your phone in settings");
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                textView.setText("You should add at least 1 fingerprint to use this feature");
            } else {
                textView.setText("Your device is ready for fingerprint authentication");
                // Your code to start fingerprint authentication goes here
            }
        }
    }
}