import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.biometric.BiometricPrompt;
import android.os.Bundle;
import android.widget.Toast;
import java.util.concurrent.Executor;
  
public class fingerprint_auth_3_Req21 extends AppCompatActivity {
  
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Executor executor = ContextCompat.getfingerprint_auth_3_Req21Executor(this);

        biometricPrompt = new BiometricPrompt(fingerprint_auth_3_Req21.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                        "Authentication error: " + errString, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(),
                        "Authentication succeeded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Cancel")
                .build();

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the create account and sign in processes for a complete solution.
        biometricPrompt.authenticate(promptInfo);
    }
}