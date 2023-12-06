import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

public class fingerprint_auth_1_Req10 {

    private CancellationSignal cancellationSignal;
    private FingerprintManager fingerprintManager;

    private void startAuth() {
        FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);

        cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                notifyUser("Authentication Error\n" + errString);
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                notifyUser("Authentication Help\n" + helpString);
            }

            @Override
            public void onAuthenticationFailed() {
                notifyUser("Authentication Failed.");
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                notifyUser("Authentication Succeeded.");
            }
        }, null);
    }

    private void notifyUser(String message) {
        Toast.makeText(fingerprint_auth_1_Req10.this, message, Toast.LENGTH_LONG).show();
    }
}