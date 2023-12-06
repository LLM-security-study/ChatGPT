import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyStore;

public class fingerprint_auth_1_Req27 extends AppCompatActivity {

    private KeyStore keyStore;
    private static final String KEY_NAME = "example_key";
    private Cipher cipher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Get an instance of Keyguard Manager and Fingerprint Manager//
            KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
            FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);


            // Check whether the device has a fingerprint sensor//
            if (!fingerprintManager.isHardwareDetected()) {
                Toast.makeText(this, "Your device doesn't support fingerprint authentication", 
                    Toast.LENGTH_LONG).show();
            }

            else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) 
                != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Please enable the fingerprint permission", Toast.LENGTH_LONG).show();
            }

            else if (!keyguardManager.isKeyguardSecure()) {
                Toast.makeText(this, "Please enable lockscreen security in your device's Settings", 
                    Toast.LENGTH_LONG).show();
            } 
            else {
                try {
                    generateKey();
                } catch (FingerprintException e) {
                    e.printStackTrace();
                }

                if (initCipher()) {
                    // If the cipher is initialized successfully, then create a CryptoObject instance//
                    FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);

                    // Here, I’m initiating the FingerprintHandler class and starting the authentication process.//
                    FingerprintHandler helper = new FingerprintHandler(this);
                    helper.startAuth(fingerprintManager, cryptoObject);
                }
            }
        }
    }