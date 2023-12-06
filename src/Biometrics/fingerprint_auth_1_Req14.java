import androidx.annotation.NonNull;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.core.os.CancellationSignal;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class fingerprint_auth_1_Req14 extends AppCompatActivity {

private FingerprintManagerCompat fingerprintManagerCompat;
private CancellationSignal cancellationSignal;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    fingerprintManagerCompat = FingerprintManagerCompat.from(this);

    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
        return;
    }
    if (fingerprintManagerCompat.isHardwareDetected() && fingerprintManagerCompat.hasEnrolledFingerprints()){
        startListeningForFingerprint();
    }
}

private void startListeningForFingerprint() {
    cancellationSignal = new CancellationSignal();
    fingerprintManagerCompat.authenticate(null, 0, cancellationSignal, authenticationCallback, null);
}

FingerprintManagerCompat.AuthenticationCallback authenticationCallback = new FingerprintManagerCompat.AuthenticationCallback() {
    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        Toast.makeText(fingerprint_auth_1_Req14.this, "Authentication error\n" + errString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        Toast.makeText(fingerprint_auth_1_Req14.this, "Authentication help\n" + helpString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
        Toast.makeText(fingerprint_auth_1_Req14.this, "Authentication succeeded!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(fingerprint_auth_1_Req14.this, "Authentication failed", Toast.LENGTH_LONG).show();
    }
};
}