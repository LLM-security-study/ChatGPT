import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.util.concurrent.Executor;

public class fingerprint_auth_1_Req13 extends AppCompatActivity {

    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Executor executor = ContextCompat.getMainExecutor(this);

        biometricPrompt = new BiometricPrompt(fingerprint_auth_1_Req13.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                // handle error here
            }

            @Override
            public void onAuthenticationSucceeded(
                    BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                // handle successful authentication here
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                // handle failed authentication
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app") // set title
            .setSubtitle("Log in using your biometric credential") // set subtitle if needed
            .setNegativeButtonText("User app password to authenticate") // set text for negative button
            .setDeviceCredentialAllowed(true) 
            .build();

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the app functionality.
        findViewById(R.id.login_button).setOnClickListener(view -> {
            biometricPrompt.authenticate(promptInfo);
        });
    }
}