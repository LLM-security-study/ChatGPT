import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.M)
public class fingerprint_auth_3_Req3 extends AppCompatActivity {

    private FingerprintManager fingerprintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Fingerprint Authentication permission not enabled", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!fingerprintManager.isHardwareDetected()) {
            Toast.makeText(this, "No fingerprint scanner detected", Toast.LENGTH_SHORT).show();
        } else if (!fingerprintManager.hasEnrolledFingerprints()) {
            Toast.makeText(this, "Register at least one fingerprint", Toast.LENGTH_SHORT).show();
        } else {
            startFingerPrintAuthentication();
        }
    }

    private void startFingerPrintAuthentication() {
        FingerprintManager.CryptoObject cryptoObject = getCryptoObject();
        fingerprintManager.authenticate(cryptoObject, new CancellationSignal(), 0, new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                // fingerprint auth is successful
                // add your functionality here
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                // fingerprint auth failed
                // you can block the user
            }
        }, null);
    }

    private FingerprintManager.CryptoObject getCryptoObject() {
        // Add your implementation to create a CryptoObject
        // This can be based on a Cipher, Signature or Mac. This depends on your use case.
        // Here we just return null for brevity.
        return null;
    }

}