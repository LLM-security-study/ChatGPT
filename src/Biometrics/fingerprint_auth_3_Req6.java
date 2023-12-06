import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.CancellationSignal;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;

public class fingerprint_auth_3_Req6Activity extends AppCompatActivity {

    private CancellationSignal cancellationSignal;
    private BiometricPrompt biometricPrompt;
    private Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        executor = ContextCompat.getfingerprint_auth_3_Req6Executor(this);
        biometricPrompt = new BiometricPrompt(fingerprint_auth_3_Req6Activity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                // Handle error
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                // Handle success
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                // Handle failed attempt
            }
        });

        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Fingerprint Authentication")
                .setDescription("Place your finger on the fingerprint scanner to authenticate.")
                .setNegativeButtonText("Cancel")
                .build();

        // Initialize the cancellation signal
        cancellationSignal = new CancellationSignal();

        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
            @Override
            public void onCancel() {
                // Handle cancellation by the user
            }
        });

        findViewById(R.id.authenticate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricPrompt.authenticate(promptInfo, cancellationSignal, executor, new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errorCode, CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                        // Handle error
                    }

                    @Override
                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        // Handle success
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        // Handle failed attempt
                    }
                });
            }
        });
    }
}