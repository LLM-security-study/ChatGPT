import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class fingerprint_auth_1_Req7 extends AppCompatActivity {

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the FingerprintManager and KeyguardManager features available in Android 6.0+ (API level 23+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager = getSystemService(FingerprintManager.class);
            keyguardManager = getSystemService(KeyguardManager.class);

            // Check if the device has a fingerprint scanner
            if (!fingerprintManager.isHardwareDetected()) {
                Toast.makeText(fingerprint_auth_1_Req7.this, "Fingerprint authentication is not supported in your device.", Toast.LENGTH_LONG).show();
            } 
            // Check if the user has granted the permission to use fingerprint in the app
            else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(fingerprint_auth_1_Req7.this, "Permission not granted to use Fingerprint Scanner", Toast.LENGTH_LONG).show();
            } 
            // Check if the user has registered at least one fingerprint on their device
            else if (!fingerprintManager.hasEnrolledFingerprints()) {
                Toast.makeText(fingerprint_auth_1_Req7.this, "No fingerprint has been setup. Visit device settings to setup your fingerprint.", Toast.LENGTH_LONG).show();
            } 
            // Check if the device is secure
            else if (!keyguardManager.isKeyguardSecure()) {
                Toast.makeText(fingerprint_auth_1_Req7.this, "Device is not secured with a PIN, Pattern or Password. Please secure your device first.", Toast.LENGTH_LONG).show();
            } 
            // If all checks are passed, go ahead with the fingerprint authentication
            else {
                Toast.makeText(fingerprint_auth_1_Req7.this, "Place your finger on the Fingerprint Scanner to access the app.", Toast.LENGTH_SHORT).show();
                fingerprintManager.authenticate(null, null, 0, new AuthenticationHandler(this), null);
            }
        }
    }
}

class AuthenticationHandler extends FingerprintManager.AuthenticationCallback {

    private fingerprint_auth_1_Req7 main;

    AuthenticationHandler(fingerprint_auth_1_Req7 main) {
        this.main = main;
    }
    
    // Callback method when the fingerprint is successfully recognized
    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        main.runOnUiThread(() -> Toast.makeText(fingerprint_auth_1_Req7.this , "Fingerprint Authentication succeeded.", Toast.LENGTH_LONG).show());
    }

    // Callback method when the fingerprint is not recognized
    @Override
    public void onAuthenticationFailed() {
        main.runOnUiThread(() -> Toast.makeText(fingerprint_auth_1_Req7.this , "Fingerprint Authentication failed.", Toast.LENGTH_LONG).show());
    }
}