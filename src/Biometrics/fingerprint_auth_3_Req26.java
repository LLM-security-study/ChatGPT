import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.CancellationSignal;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.widget.Toast;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class fingerprint_auth_3_Req26 extends AppCompatActivity {

    private CancellationSignal cancellationSignal;
    private static final String KEY_NAME = "yourKey";

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Executor executor = Executors.newSingleThreadExecutor();

        final BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(this)
                .setTitle("Fingerprint Authentication")
                .setSubtitle("Subtitle")
                .setDescription("Description")
                .setNegativeButton("Cancel", executor, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //User clicked on cancel, operation aborted
                    }
                }).build();

        biometricPrompt.authenticate(new CancellationSignal(), executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                fingerprint_auth_3_Req26.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Authentication succeeded
                        Toast.makeText(fingerprint_auth_3_Req26.this, "Authentication succeeded!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private CancellationSignal getCancellationSignal(){
        cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
            @Override
            public void onCancel() {
                //Handle the cancel event
            }
        });
        return cancellationSignal;
    }

    private boolean isFingerPrintAuthAvailable(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_FINGERPRINT);
    }
}