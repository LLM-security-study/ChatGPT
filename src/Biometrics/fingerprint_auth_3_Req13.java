import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;

public class fingerprint_auth_3_Req13 extends FingerprintManager.AuthenticationCallback {

    private FingerprintManager fingerprintManager;
    private CancellationSignal cancellationSignal;

    public fingerprint_auth_3_Req13(FingerprintManager fm) {
        fingerprintManager = fm;
    }

    public boolean isFingerprintSupported() {
        return fingerprintManager.isHardwareDetected() &&
               fingerprintManager.hasEnrolledFingerprints();
    }

    public void startListening(FingerprintManager.CryptoObject cryptoObject) {
        cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this,
                null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
        // Handle error
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        super.onAuthenticationHelp(helpCode, helpString);
        // Handle help request
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        // User is authenticated successfully
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        // Handle failed authentication
    }
}