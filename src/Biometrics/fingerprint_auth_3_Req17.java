import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;

import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class fingerprint_auth_3_Req17 {

    private static final String KEY_NAME = "app_finger";
    private Cipher cipher;
    private KeyStore keyStore;

    @TargetApi(28)
    private void stepUpBiometricPrompt() {
        //TODO - Setup biometricPrompt for API>=28 (You will also need to define Authentication callback here)
    }

    private void setupFingerPrint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            KeyGenerator keyGenerator = null;
            try {
                keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
                keyStore.load(null);
                keyGenerator.init(new
                        KeyGenParameterSpec.Builder(KEY_NAME,
                        KeyProperties.PURPOSE_ENCRYPT |
                        KeyProperties.PURPOSE_DECRYPT)
                        .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                        .setUserAuthenticationRequired(true)
                        .setEncryptionPaddings(
                                KeyProperties.ENCRYPTION_PADDING_PKCS7)
                        .build());
                keyGenerator.generateKey();

            } catch (Exception e) {
               //TODO - handle error
            }
        }
    }

    @TargetApi(28)
    public void authenticateFingerPrint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
            if (fingerprintManager.isHardwareDetected() && fingerprintManager.hasEnrolledFingerprints()) {
                setupFingerPrint();
            }
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && checkSelfPermission(Manifest.permission.USE_BIOMETRIC) == PackageManager.PERMISSION_GRANTED) {
            stepUpBiometricPrompt();
        }
    }
}