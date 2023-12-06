import android.Manifest;
import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class fingerprint_auth_3_Req2Activity extends AppCompatActivity {

    // Declare a string variable for the key we’re going to use in our fingerprint authentication
    private static final String KEY_NAME = "yourKey";
    private Cipher cipher;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private TextView textView;
    private FingerprintManager.CryptoObject cryptoObject;
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        // Initializing both Android Keyguard Manager and Fingerprint Manager        
        keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        
        textView = (TextView) findViewById(R.id.textView);
        
        // Check whether the device has a fingerprint sensor
        if(!fingerprintManager.isHardwareDetected()){
            textView.setText("Your device doesn't support fingerprint authentication");
        }
        // check that the user has granted your app the USE_FINGERPRINT permission
        else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            textView.setText("Please enable the fingerprint permission");
        }
        // check that the user has registered at least one fingerprint
        else if (!fingerprintManager.hasEnrolledFingerprints()) {
            textView.setText("No fingerprint configured. Please register at least one fingerprint in your device's Settings");
        }
        // check that the lockscreen is secured
        else if (!keyguardManager.isKeyguardSecure()) {
            textView.setText("Please enable lockscreen security in your device's Settings");
        } 
        else {
            try {
                generateKey();
            } catch (FingerprintException e) {
                e.printStackTrace();
            }
            
            if (initCipher()) {
                cryptoObject = new FingerprintManager.CryptoObject(cipher);
                FingerprintHandler helper = new FingerprintHandler(this);
                helper.startAuth(fingerprintManager, cryptoObject);
            }
        }
    }

    // Create the generateKey method that we’ll use to gain access to the Android keystore and generate the encryption key//

    private void generateKey() throws FingerprintException {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            
            keyStore.load(null);
            keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT | 
                        KeyProperties.PURPOSE_DECRYPT).setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            
            keyGenerator.generateKey();
            
        } catch (KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException
                | InvalidAlgorithmParameterException | CertificateException | IOException exc) {
            exc.printStackTrace();
            throw new FingerprintException(exc);
        }
    }
    // Create a new method that we’ll use to initialize our cipher//
    public boolean initCipher() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }

        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME, null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            
            return true;
            
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
            
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }

    private class FingerprintException extends Exception {
        public FingerprintException(Exception e) {
            super(e);
        }
    }
}