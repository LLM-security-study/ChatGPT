import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.security.KeyStore;

public class fingerprint_auth_2_Req24Activity extends AppCompatActivity {

    private KeyStore keyStore;
    private static final String KEY_NAME = "FingerPrintAuthKey";
    private Cipher cipher;

    FingerprintManager fingerprintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            authenticateUserViaFingerprint();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void authenticateUserViaFingerprint() {

        // Check if the Fingerprint Sensor is available
        if (!fingerprintManager.isHardwareDetected()) {
            Toast.makeText(this, "Fingerprint Sensor not detected in Device", Toast.LENGTH_LONG).show();
        } else {
            // Check if permission is already granted.
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Fingerprint authentication permission not enabled.", Toast.LENGTH_LONG).show();
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                Toast.makeText(this, "Register at least one fingerprint in Settings", Toast.LENGTH_LONG).show();
            } else {
                // All necessary permissions are granted, sensor is available and at least one fingerprint is registered. Start listening for a fingerprint scan.
                FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                FingerprintHelper fingerprintHelper = new FingerprintHelper(this, cryptoObject);
                fingerprintHelper.startFingerprintAuthentication();
            }
        }
    }
}