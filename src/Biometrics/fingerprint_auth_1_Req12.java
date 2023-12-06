import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;

public class fingerprint_auth_1_Req12 {

    private FingerprintManager fingerprintManager;
    private CancellationSignal cancellationSignal;

    public fingerprint_auth_1_Req12(FingerprintManager fingerprintManager){
        this.fingerprintManager = fingerprintManager;
    }

    public void startAuth() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
  
        cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(null, cancellationSignal, 0, new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                // called when a fatal error has occurred
                // errorCode will have the error code and errString will be the error messsage
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                // Called when a non-fatal error has occurred
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                // Called when a fingerprint has been successfully matched to the associated user
                // the result object will have the details of the authentication
            }

            @Override
            public void onAuthenticationFailed() {
                // Called when the fingerprint doesn’t match with any of the fingerprints registered on the device
            }
        }, null);
    }
}