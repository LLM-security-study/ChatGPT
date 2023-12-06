import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;

public class fingerprint_auth_1_Req5 extends AppCompatActivity {

    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Executor executor = ContextCompat.getMainExecutor(this);

        biometricPrompt = new BiometricPrompt(fingerprint_auth_1_Req5.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                // Authentication error, you may want to handle it here.
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                // Authentication succeed!
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                // Authentication failed.
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric authentication")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Cancel")
                .build();

        // Prompt appears when user click "Log in".
        // Consider integrating it with the logic in onResume() or onStart().
   }

    @Override
    protected void onResume() {
        super.onResume();
        biometricPrompt.authenticate(promptInfo);
    }
}