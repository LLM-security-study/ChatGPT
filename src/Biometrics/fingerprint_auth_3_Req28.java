import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;

public class fingerprint_auth_3_Req28 {

    private CancellationSignal cancellationSignal;
    private Context appContext;

    public fingerprint_auth_3_Req28(Context context) {
        appContext = context;
    }

    public void startAuthentication(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        cancellationSignal = new CancellationSignal();
        if (manager != null) {
            manager.authenticate(cryptoObject, cancellationSignal, 0, new AuthenticationHandler(), null);
        }
    }

    private class AuthenticationHandler extends FingerprintManager.AuthenticationCallback {
        @Override
        public void onAuthenticationError(int errorCode, CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
            // Expose error to the user (use a toast, dialog, etc.)
        }

        @Override
        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
            super.onAuthenticationHelp(helpCode, helpString);
            // Provide help to the user, hints about the finger etc.
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            // Notify the user that the authentication was successful
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            // Notify user that the authentication failed
        }
      }
}