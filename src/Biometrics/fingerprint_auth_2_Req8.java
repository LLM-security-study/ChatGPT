import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.Toast;
import androidx.core.content.ContextCompat;

// This class will listen for fingerprint events
class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private CancellationSignal cancellationSignal;
    private Context appContext;

    public FingerprintHandler(Context context) {
        appContext = context;
    }

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        
        cancellationSignal = new CancellationSignal();
        if (ContextCompat.checkSelfPermission(appContext, Manifest.permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED) {
            manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
        }
        
    }

    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        Toast.makeText(appContext, "Authentication error\n" + errString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        Toast.makeText(appContext, "Authentication help\n" + helpString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(appContext, "Authentication failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        Toast.makeText(appContext, "Success!", Toast.LENGTH_LONG).show();
    }

}


public class fingerprint_auth_2_Req8 {

    public static void main(String[] args) {
        
        // Initialize the context, crypto object and fingerprint manager 
        Context context = ... // Get the application context
        FingerprintManager fingerprintManager = ... // Get the fingerprint manager
        FingerprintManager.CryptoObject cryptoObject = ... // Initialize the crypto object

        FingerprintHandler fingerprintHandler = new FingerprintHandler(context);
        fingerprintHandler.startAuth(fingerprintManager, cryptoObject);

    }

}