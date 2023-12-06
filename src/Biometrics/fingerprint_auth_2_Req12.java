import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.os.CancellationSignal;
import android.widget.Toast;
import android.os.Bundle;
import android.app.Activity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class fingerprint_auth_2_Req12 extends Activity {

    private CancellationSignal cancellationSignal;
    
    private Executor executor = Executors.newSingleThreadExecutor(); 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(this)
            .setTitle("Fingerprint Authentication")
            .setSubtitle("Authenticate yourself to proceed")
            .setNegativeButton("Cancel", executor, new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                cancellationSignal.cancel();
              }
            }).build();

        biometricPrompt.authenticate(getCancellationSignal(), executor, getAuthenticationCallback());
    }


    private BiometricPrompt.AuthenticationCallback getAuthenticationCallback(){
        return new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(), "Authentication error: " + errString, Toast.LENGTH_SHORT)
                    .show();
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(), "Authentication succeeded!", Toast.LENGTH_SHORT)
                    .show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT)
                    .show();
            }
        };
    }

    private CancellationSignal getCancellationSignal() {
        cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Finger print authentication was cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        return cancellationSignal;
    }
}