import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class fingerprint_auth_2_Req29 {

    private CancellationSignal cancellationSignal;
    private Executor executor;

    public fingerprint_auth_2_Req29() {
        executor = Executors.newSingleThreadExecutor();
    }

    public void authenticateUser(final FingerPrintAuthCallBack fingerPrintAuthCallBack) {

        BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(context)
                .setTitle("Fingerprint Authorization")
                .setSubtitle("Provide your FingerPrint for Authentication")
                .setDescription("This app is using Android's Biometric security to protect your data")
                .setNegativeButton("Cancel", executor, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fingerPrintAuthCallBack.authenticationCancelled();
                        cancellationSignal.cancel();
                    }
                }).build();

        biometricPrompt.authenticate(getCancellationSignal(), executor, getAuthenticationCallBack(fingerPrintAuthCallBack));
    }

    private BiometricPrompt.AuthenticationCallback getAuthenticationCallBack(final FingerPrintAuthCallBack fingerPrintAuthCallBack) {
        return new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                fingerPrintAuthCallBack.authenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                fingerPrintAuthCallBack.authenticationSuccessful(result);
            }

            @Override
            public void onAuthenticationFailed() {
                fingerPrintAuthCallBack.authenticationFailed();
            }
        };
    }

    private CancellationSignal getCancellationSignal() {
        cancellationSignal = new CancellationSignal();
        return cancellationSignal;
    }

    public interface FingerPrintAuthCallBack {
        void authenticationSuccessful(BiometricPrompt.AuthenticationResult result);
        void authenticationError(int errorCode, CharSequence errString);
        void authenticationCancelled();
        void authenticationFailed();
    }
}