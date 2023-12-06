import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

public class fingerprint_auth_3_Req8 {

    private CancellationSignal cancellationSignal;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void authenticateUser(FingerprintManager fingerprintManager) {
        cancellationSignal = new CancellationSignal();

        if (ActivityCompat.checkSelfPermission(fingerprint_auth_3_Req8.this, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fingerprintManager.authenticate(null, cancellationSignal, 0, new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(fingerprint_auth_3_Req8.this, "Authentication was successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(fingerprint_auth_3_Req8.this, "Authentication failed", Toast.LENGTH_LONG).show();
            }
        }, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void stopListening() {
        cancellationSignal.cancel();
    }
}