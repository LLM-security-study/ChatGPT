import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

public class fingerprint_auth_1_Req22 extends AppCompatActivity {

    private FingerprintManager fingerprintManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onStart() {
        super.onStart();

        // Check if the Fingerprint sensor is available
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        if (!fingerprintManager.isHardwareDetected()) {
            // Device doesn't support fingerprint authentication     
            return;
        }

        // Checks if app has the USE_FINGERPRINT permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            // For this exercise it is assumed that the app already has the necessary permission
            // In a real world application, prompt user for permission here
        }

        if (!fingerprintManager.hasEnrolledFingerprints()) {
            // User hasn't registered any fingerprints to authenticate with 
            return;
        }

        // Everything is ready for fingerprint authentication
        startAuth(fingerprintManager, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        FingerprintHandler helper = new FingerprintHandler(this);
        helper.startAuth(manager, cryptoObject);
    }
}