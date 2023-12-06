import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;

public class fingerprint_auth_1_Req26 extends AppCompatActivity implements FingerprintHandler.AuthenticationCallback {

    private FingerprintHandler mFingerprintHandler;
    private FingerprintManager mFingerprintManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mFingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        
        checkFingerprintPermission();
        
        mFingerprintHandler = new FingerprintHandler(this);
        
        startFingerprintAuthentication();
    }

    private void checkFingerprintPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Fingerprint authentication permission not enabled", Toast.LENGTH_LONG).show();
            return;
        }

        if (!mFingerprintManager.isHardwareDetected()) {
            Toast.makeText(this, "Your device doesn't support fingerprint authentication", Toast.LENGTH_LONG).show();
            return;
        }

        if (!mFingerprintManager.hasEnrolledFingerprints()) {
            Toast.makeText(this, "No fingerprint enrolled on this device", Toast.LENGTH_LONG).show();
            return;
        }
    }

    private void startFingerprintAuthentication() {
        mFingerprintHandler.startAuth(mFingerprintManager, new CancellationSignal());
    }

    @Override
    public void onAuthenticationSuccess() {
        Toast.makeText(this, "Authentication succeeded!", Toast.LENGTH_LONG).show();
        // Start your authenticated activity here.
    }

    @Override
    public void onAuthenticationFail() {
        Toast.makeText(this, "Authentication failed", Toast.LENGTH_LONG).show();
    }
}