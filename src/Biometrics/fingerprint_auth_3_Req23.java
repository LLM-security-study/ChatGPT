import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.Toast;

public class fingerprint_auth_3_Req23 {

    private CancellationSignal cancellationSignal;
    private FingerprintManager fingerprintManager;

    public fingerprint_auth_3_Req23(FingerprintManager fingerprintManager) {
        this.fingerprintManager = fingerprintManager;
    }

    public void startAuth() {
        cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(null, cancellationSignal, 0, new AuthenticationCallback(), null);
    }

    private class AuthenticationCallback extends FingerprintManager.AuthenticationCallback {

        @Override
        public void onAuthenticationError(int errorCode, CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
            Toast.makeText(getAppContext(), "Authentication error: " + errString, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            Toast.makeText(getAppContext(), "Authentication succeeded!", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            Toast.makeText(getAppContext(), "Authentication failed!", Toast.LENGTH_LONG).show();
        }
    }
}