import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class fingerprint_auth_3_Req1 extends AppCompatActivity {

    private TextView mHeadingLabel;
    private FingerprintManager mFingerprintManager;
    private KeyguardManager mKeyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHeadingLabel = findViewById(R.id.headingLabel);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mFingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            mKeyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if (!mFingerprintManager.isHardwareDetected()) {
                mHeadingLabel.setText("Fingerprint Scanner not detected in Device");
            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) !=
                    PackageManager.PERMISSION_GRANTED) {
                mHeadingLabel.setText("Permission not granted to use Fingerprint Scanning");
            } else if (!mKeyguardManager.isKeyguardSecure()) {
                mHeadingLabel.setText("Add Lock to your Phone in Settings");
            } else if (!mFingerprintManager.hasEnrolledFingerprints()) {
                mHeadingLabel.setText("You should add atleast 1 Fingerprint to use this Feature");
            } else {
                mHeadingLabel.setText("Place your Finger on Scanner to Access the App.");
                FingerprintHandler fingerprintHandler = new FingerprintHandler(this);
                fingerprintHandler.startAuth(mFingerprintManager, null);
            }
        }
    }
}