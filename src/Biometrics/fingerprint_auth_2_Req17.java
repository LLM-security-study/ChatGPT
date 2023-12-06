import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.widget.Toast;
import androidx.core.content.ContextCompat;

public class fingerprint_auth_2_Req17 {
    private FingerprintManager fingerprintManager;
    private CancellationSignal cancellationSignal;
    private KeyguardManager keyguardManager;

    public fingerprint_auth_2_Req17(FingerprintManager fingerprintManager, KeyguardManager keyguardManager) {
        this.fingerprintManager = fingerprintManager;
        this.keyguardManager = keyguardManager;
    }

    public void startAuth() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Fingerprint authentication permission not enabled", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!fingerprintManager.isHardwareDetected()) {
                Toast.makeText(getApplicationContext(), "Your device doesn't support fingerprint authentication", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!keyguardManager.isKeyguardSecure()) {
                Toast.makeText(getApplicationContext(), "Lock screen security not enabled in settings", Toast.LENGTH_SHORT).show();
                return;
            }

            fingerprintManager.authenticate(null, cancellationSignal, 0, new FingerprintManager.AuthenticationCallback() {
                @Override
                public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Toast.makeText(getApplicationContext(), "Fingerprint recognised successfully", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    Toast.makeText(getApplicationContext(), "Fingerprint recognition failed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationError(int errorCode, CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    Toast.makeText(getApplicationContext(), "Fingerprint recognition error: " + errString, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                    super.onAuthenticationHelp(helpCode, helpString);
                    Toast.makeText(getApplicationContext(), "Fingerprint recognition help: " + helpString, Toast.LENGTH_SHORT).show();
                }
            }, null);
        } else {
            Toast.makeText(getApplicationContext(), "Fingerprint authentication not supported in this version", Toast.LENGTH_SHORT).show();
        }
    }
}