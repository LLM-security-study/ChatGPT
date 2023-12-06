import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

@RequiresApi(api = Build.VERSION_CODES.M)
public class fingerprint_auth_2_Req26 {
    private KeyguardManager keyguardManager;
    private FingerprintManager fingerprintManager;

    public fingerprint_auth_2_Req26(KeyguardManager keyguardManager, FingerprintManager fingerprintManager) {
        this.keyguardManager = keyguardManager;
        this.fingerprintManager = fingerprintManager;
    }

    public boolean isFingerPrintAvailable() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && fingerprintManager.isHardwareDetected()
                && fingerprintManager.hasEnrolledFingerprints();
    }

    public boolean isKeyguardSecured() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && keyguardManager.isKeyguardSecure();
    }
    
    public boolean checkPrivileges() {
        if (ActivityCompat.checkSelfPermission(YourActivity.this,
                Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        
        return true;
    }

    // ... additional methods for processing an authentication attempt, etc ...
}