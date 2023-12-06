import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class fingerprint_auth_3_Req25 extends AppCompatActivity {
    private TextView fingerprintStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fingerprintStatus = (TextView) findViewById(R.id.fingerprint_status);
        
        FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        
        //Check permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            fingerprintStatus.setText("Set the permission to use fingerprint scanner in app settings");
            return;
        }
        
        //Check if device has fingerprint scanner
        if (!fingerprintManager.isHardwareDetected()) {
            fingerprintStatus.setText("Your Device does not have a Fingerprint Sensor");
        } else {
            // Checks whether fingerprint permission is set on manifest
            if (!fingerprintManager.hasEnrolledFingerprints()) {
                fingerprintStatus.setText("Register at least one fingerprint in Settings");
            } else {
                authenticate(fingerprintManager);
            }
        }
    }
    
    private void authenticate(FingerprintManager fingerprintManager){
        //Create callback to handle authentication events
        FingerprintManager.AuthenticationCallback authenticationCallback = new FingerprintManager.AuthenticationCallback(){
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                fingerprintStatus.setText("Authentication failed");
            }
            public void onAuthenticationFailed() {
                fingerprintStatus.setText("Authentication failed");
            }  
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                fingerprintStatus.setText("Success!");
            }
        };

        //Begin authentication
       fingerprintManager.authenticate(null, null, 0, authenticationCallback, null);
    }
}