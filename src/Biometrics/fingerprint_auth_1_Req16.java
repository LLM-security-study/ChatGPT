import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class fingerprint_auth_1_Req16 extends AppCompatActivity {

    private CancellationSignal cancellationSignal;
    private Executor executor;

    @SuppressLint("NewApi")
    private BiometricPrompt.AuthenticationCallback authenticationCallback = new BiometricPrompt.AuthenticationCallback() {
        @Override
        public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
            notifyUser("Authenticate error - error code: " + errorCode);
        }

        @Override
        public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            notifyUser("Authenticate successful");
        }
    };

    @SuppressLint("NewApi")
    private BiometricPrompt getBiometricPrompt(){
        return new BiometricPrompt.Builder(this)
                .setTitle("Fingerprint Authentication")
                .setSubtitle("Subtitle")
                .setDescription("This app uses fingerprint protection to keep your data secure.")
                .setNegativeButton("Cancel", executor, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notifyUser("Authentication was cancelled by the user.");
                    }
                })
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executor = Executors.newSingleThreadExecutor(); 
        checkBiometricSupport();

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
            notifyUser("Fingerprint authentication permission not enabled");
        } else {
            getBiometricPrompt().authenticate(getCancellationSignal(), executor, authenticationCallback);
        }
    }

    private boolean checkBiometricSupport() {
        PackageManager packageManager = this.getPackageManager();
        if(!packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            notifyUser("Fingerprint sensor not supported in device");
            return false;
        }
        return true;
    }

    private void notifyUser(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private CancellationSignal getCancellationSignal() {
        cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
            @Override
            public void onCancel() {
                notifyUser("Authentication was cancelled by the user");
            }
        });

        return cancellationSignal;
    }
}