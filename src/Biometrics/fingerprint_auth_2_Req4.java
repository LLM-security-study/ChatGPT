import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import androidx.core.app.ActivityCompat;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;

@TargetApi(Build.VERSION_CODES.M)
public class fingerprint_auth_2_Req4 extends FingerprintManager.AuthenticationCallback {
    private CancellationSignal cancellationSignal;
    private FingerprintManager fingerprintManager;
    
    public void startAuth() {
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            // Ask for fingerprint permission
            return;
        }

        KeyStore keyStore;
        KeyGenerator keyGenerator;
        Cipher cipher;

        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            keyGenerator.init(new KeyGenParameterSpec.Builder("MyKey", KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
            
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES+'/'+KeyProperties.BLOCK_MODE_CBC+'/'+KeyProperties.ENCRYPTION_PADDING_PKCS7);
            cipher.init(Cipher.ENCRYPT_MODE, keyStore.getKey("MyKey", null));
            FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
            
            cancellationSignal = new CancellationSignal();
            fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        // Handle errors 
    }
    
    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        // Show help info
    }
    
    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        // Authentication was successful
    }
    
    @Override
    public void onAuthenticationFailed() {
        // Authentication failed
    }
}