import android.Manifest;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.security.KeyStore;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class fingerprint_auth_3_Req22Activity extends AppCompatActivity {
  private KeyStore keyStore;
  private static final String KEY_NAME = "your_key_name";
  private Cipher cipher;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
    FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

    if (!fingerprintManager.isHardwareDetected()) {
      // Device doesn't support fingerprint authentication     
    } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
      // User hasn't granted permission to use Fingerprint scanner
    } else if (!keyguardManager.isKeyguardSecure()) {
      // The device doesn't have a 'secure lock screen'
    } else {
      generateKey();

      if (initCipher()) {
        FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, new AuthenticationHandler(this), null);
      }
    }
  }

  private void generateKey() {
    try {
      keyStore = KeyStore.getInstance("AndroidKeyStore");
      KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

      keyStore.load(null);
      keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT |
        KeyProperties.PURPOSE_DECRYPT).setBlockModes(KeyProperties.BLOCK_MODE_CBC)
        .setUserAuthenticationRequired(true)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
        .build());
      keyGenerator.generateKey();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean initCipher() {
    try {
      cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
    } catch (Exception e) {
      return false;
    }

    try {
      keyStore.load(null);
      SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME, null);
      cipher.init(Cipher.ENCRYPT_MODE, key);
      return true;
    } catch (KeyPermanentlyInvalidatedException e) {
      return false;
    } catch (Exception e) {
      return false;
    }
  }
   
  private class AuthenticationHandler extends FingerprintManager.AuthenticationCallback {
    private final fingerprint_auth_3_Req22Activity activity;

    AuthenticationHandler(fingerprint_auth_3_Req22Activity activity) {
      this.activity = activity;
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
      Toast.makeText(activity, "Authentication succeeded!", Toast.LENGTH_LONG).show();
      // Here you can start a new activity if the authentication is successful
    }

    @Override
    public void onAuthenticationFailed() {
      Toast.makeText(activity, "Authentication failed", Toast.LENGTH_LONG).show();
    }
  }
}