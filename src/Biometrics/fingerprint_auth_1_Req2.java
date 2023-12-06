// This is just an example and will not be able to run without the Android SDK
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.core.os.CancellationSignal;
import android.widget.Toast;

public class fingerprint_auth_1_Req2 extends AppCompatActivity {

    private TextView txtView;
    private FingerprintManagerCompat fingerprintManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = findViewById(R.id.txtView);
        fingerprintManagerCompat = FingerprintManagerCompat.from(this);

        startAuthentication();
    }

    private void startAuthentication() {
        if(FingerprintManagerCompat.from(this).isHardwareDetected()
            && FingerprintManagerCompat.from(this) .hasEnrolledFingerprints()) {
            authenticateUser();
        } else {
            txtView.setText("Fingerprint authentication not enabled or supported");
        }

    }

    private void authenticateUser() {

        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManagerCompat.authenticate(null, 0, cancellationSignal, 
          new FingerprintManagerCompat.AuthenticationCallback() {

            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                txtView.setText("An authentication error occurred");
            }

            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                txtView.setText("Authentication help: " + helpString);
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                txtView.setText("Authentication succeeded!");
            }

            @Override
            public void onAuthenticationFailed() {
                txtView.setText("Authentication failed");
            }
        }, null);
    }
}