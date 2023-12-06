import android.content.Context;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.CancellationSignal;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

public class fingerprint_auth_3_Req5 {

    private CancellationSignal cancellationSignal;
    private Context context;

    public fingerprint_auth_3_Req5(Context context){
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public BiometricPrompt getBiometricPrompt(BiometricPrompt.AuthenticationCallback authenticationCallback) {
        Executor newExecutor = Executors.newSingleThreadExecutor();
        BiometricPrompt myBiometricPrompt = new BiometricPrompt.Builder(context)
                .setTitle("Fingerprint Authentication")
                .setSubtitle("Please place your finger on the fingerprint sensor to authenticate.")
                .setDescription("")
                .setNegativeButton("Cancel", newExecutor, (dialogInterface, i) -> {})
                .build();
        return myBiometricPrompt;
    }

    public BiometricPrompt.AuthenticationCallback getAuthenticationCallback(){
        return new BiometricPrompt.AuthenticationCallback() {
            //handling different events
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(context, "Authentication succeeded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private CancellationSignal getCancellationSignal(){
        cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(() -> Toast.makeText(context,"Authentication was cancelled by the user.", Toast.LENGTH_LONG).show() );
        return cancellationSignal;
    }
}