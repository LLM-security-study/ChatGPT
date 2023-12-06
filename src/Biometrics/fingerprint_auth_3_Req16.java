import android.content.Context;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.CancellationSignal;
import androidx.annotation.RequiresApi;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class fingerprint_auth_3_Req16 {
    private Context context;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @RequiresApi(api = Build.VERSION_CODES.P)  // BiometricPrompt requires API level 28 (Pie) or higher
    public fingerprint_auth_3_Req16(Context context) {
        this.context = context;
        this.executor = Executors.newSingleThreadExecutor();
        this.biometricPrompt = new BiometricPrompt.Builder(context)
                .setTitle("Fingerprint Authentication")
                .setSubtitle("Please place your finger on the fingerprint scanner.")
                .setDescription("Your app is using Android biometric authentication.")
                .setNegativeButton("Cancel", this.executor, (dialogInterface, i) -> {
                    // User clicked the "Cancel" button
                })
                .build();

        this.promptInfo = new BiometricPrompt.PromptInfo.Builder().build();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void authenticate() {
        this.biometricPrompt.authenticate(this.promptInfo, new CancellationSignal(), this.executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                // Authentication error, notify the user. (E.g. errorCode = BiometricPrompt.ERROR_NO_BIOMETRICS, errString = "No biometrics enrolled.")
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                // Authentication is successful.
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                // Authentication failed, notify the user.
            }
        });
    }
}