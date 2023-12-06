import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import android.util.Log;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyStore;
import androidx.core.app.ActivityCompat;

public class fingerprint_auth_2_Req19 {

    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private FingerprintManagerCompat fingerprintManager;
    private CancellationSignal cancellationSignal;
    private Cipher cipher;

    public fingerprint_auth_2_Req19(Context context) {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }
        fingerprintManager = FingerprintManagerCompat.from(context);
        if (!fingerprintManager.isHardwareDetected()) {
            return; // Device doesn't support fingerprint auth
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
            return; // User hasn't granted permission to use Fingerprint
        }
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        if (!keyguardManager.isKeyguardSecure()) {
            return; // User hasn't setup a fingerprint or lock screen
        }
        try {
            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();

            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME, null);
            cipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/"
                            + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
            FingerprintManagerCompat.AuthenticationCallback callback = new AuthenticationCallback();
            fingerprintManager.authenticate(cryptoObject, 0, cancellationSignal, callback, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class AuthenticationCallback extends FingerprintManagerCompat.AuthenticationCallback {
        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            super.onAuthenticationError(errMsgId, errString);
            Log.e("FingerprintAuth", "Error: " + errMsgId + " - " + errString);
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            Log.i("FingerprintAuth", "Success!");
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            Log.e("FingerprintAuth", "Failed to authenticate");
        }
    }

}