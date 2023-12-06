// Import appropriate packages
import android.hardware.fingerprint.FingerprintManager;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;

public class fingerprint_auth_1_Req21{
    
    private CancellationSignal cancellationSignal;
    private final Context appContext;

    public fingerprint_auth_1_Req21(Context context){
        appContext = context;
    }

    // Check if the user has set up fingerprint security in their system settings. 
    public boolean checkFinger(){
        FingerprintManager fingerprintManager = (FingerprintManager) appContext.getSystemService(Context.FINGERPRINT_SERVICE);
        return fingerprintManager.isHardwareDetected() && fingerprintManager.hasEnrolledFingerprints();
    }

    // Trigger the fingerprint authentication
    public void authUser(FingerprintManager.AuthenticationCallback callback){
        FingerprintManager fingerprintManager = (FingerprintManager) appContext.getSystemService(Context.FINGERPRINT_SERVICE);

        if (ActivityCompat.checkSelfPermission(appContext, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        
        fingerprintManager.authenticate(null, cancellationSignal, 0, callback, null);
    }
}

class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
    // handle the fingerprint authentication events.
    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
        // Handle Error
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        // Handle failure
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        //Handle success
    }
}