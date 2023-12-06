import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.Toast;
import android.content.Context;

public class fingerprint_auth_2_Req14 {

    private FingerprintManager fingerprintManager;
    private CancellationSignal cancellationSignal;

    public fingerprint_auth_2_Req14(Context context){
        fingerprintManager = context.getSystemService(FingerprintManager.class);
    }

    public void startAuth(){
        cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(null, cancellationSignal, 0, new AuthenticationCallback(), null);
    }

    private class AuthenticationCallback extends FingerprintManager.AuthenticationCallback {
        @Override
        public void onAuthenticationError(int errorCode, CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
            Toast.makeText(getApplication(), "Authentication Error", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            Toast.makeText(getApplication(), "Authentication Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            Toast.makeText(getApplication(), "Authentication Succeeded", Toast.LENGTH_SHORT).show();              
        }
    }

    public void cancelAuth(){
        cancellationSignal.cancel();
    }
}