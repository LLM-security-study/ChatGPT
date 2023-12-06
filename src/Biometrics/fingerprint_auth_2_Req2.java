import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class fingerprint_auth_2_Req2Activity extends AppCompatActivity {
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        executor = ContextCompat.getfingerprint_auth_2_Req2Executor(this);
        biometricPrompt = new BiometricPrompt(fingerprint_auth_2_Req2Activity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                showMessage("Authentication error: " + errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                showMessage("Authentication succeeded!");
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                showMessage("Authentication failed");
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Fingerprint Auth")
                .setSubtitle("Please place your fingerprint for authentication")
                .setNegativeButtonText("Use account password")
                .build();

        findViewById(R.id.authButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biometricPrompt.authenticate(promptInfo);
            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(fingerprint_auth_2_Req2Activity.this, message, Toast.LENGTH_SHORT).show();
    }
}