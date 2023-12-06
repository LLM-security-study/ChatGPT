import android.content.Context;
import android.hardware.biometrics.BiometricPrompt;
import android.os.CancellationSignal;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class fingerprint_auth_2_Req3 {

    private final Executor executor;
    private final BiometricPrompt biometricPrompt;
    private final BiometricPrompt.PromptInfo promptInfo;

    public fingerprint_auth_2_Req3(Context context) {
          executor = Executors.newSingleThreadExecutor();

          biometricPrompt = new BiometricPrompt.Builder(context)
                            .setTitle("Fingerprint Auth")
                            .setSubtitle("Subtitle")
                            .setDescription("Place your finger on the sensor.")
                            .setNegativeButton("Cancel", executor, (dialogInterface, i) -> {})
                            .build();

          promptInfo = new BiometricPrompt.PromptInfo.Builder()
                            .setTitle("Fingerprint Auth")
                            .setSubtitle("Subtitle")
                            .setDescription("Description")
                            .setNegativeButtonText("Cancel")
                            .build();
     }

     public void authenticateUser(BiometricPrompt.AuthenticationCallback authenticationCallback) {
       biometricPrompt.authenticate(promptInfo, new CancellationSignal(), executor, authenticationCallback);
    }

}