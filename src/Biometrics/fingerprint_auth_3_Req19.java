import android.os.Bundle;
import android.util.Log;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

public class fingerprint_auth_3_Req19 extends AppCompatActivity {
  private BiometricPrompt.PromptInfo promptInfo;
  private BiometricPrompt biometricPrompt;
  private final Executor executor = Executors.newSingleThreadExecutor();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
      @Override
      public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
        Log.e("Biometric", "Authentication Error : " + errString);
      }

      @Override
      public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        Log.d("Biometric", "Authentication Succeeded");
      }

      @Override
      public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        Log.e("Biometric", "Authentication Failed");
      }
    });
    
    promptInfo = new BiometricPrompt.PromptInfo.Builder()
      .setTitle("Fingerprint Authentication")
      .setSubtitle("Please place your finger on the sensor to authenticate.")
      .setNegativeButtonText("Cancel")
      .build();

    biometricPrompt.authenticate(promptInfo);
  }
}