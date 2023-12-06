import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;

public class fingerprint_auth_2_Req30 {

    //Instance of FingerprintManager
    private FingerprintManager fingerprintManager;
    //Instance of CancellationSignal
    private CancellationSignal cancellationSignal;

    public void authenticate(FingerprintManager fpManager) {
        this.fingerprintManager = fpManager;
        this.cancellationSignal = new CancellationSignal();

        checkFingerprintPermissions();

        //Call for fingerprint authentication
        this.fingerprintManager.authenticate(null, this.cancellationSignal, 0, getAuthCallback(), null);
    }

    //Check for necessary permissions
    private void checkFingerprintPermissions() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED){
            //You should request permission from user here
        }
    }

    //Creating a new FingerprintManager.AuthenticationCallback object
    private FingerprintManager.AuthenticationCallback getAuthCallback() {
        return new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                //Called when a fatal error has occurred.
                /* handle error scenario */
            }
            
            @Override
            public void onAuthenticationFailed() {
                //Called when the fingerprint doesn’t match with any of the fingerprints registered on the device
                /* handle failure scenario */
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                //Called when a fingerprint is successfully recognized.
                /* handle success scenario */
            }
        };
    }
}