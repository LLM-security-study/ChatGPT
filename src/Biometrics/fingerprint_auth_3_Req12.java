import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

@RequiresApi(api = Build.VERSION_CODES.M)
public class fingerprint_auth_3_Req12 extends AppCompatActivity {

    private TextView mParaLabel;
    private FingerprintManager mFingerprintManager;
    private FingerprintHandler mFingerprintHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mParaLabel = (TextView) findViewById(R.id.paraLabel);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            mParaLabel.setText("Fingerprint Authentication permission not enabled");
        }else{
            if (!mFingerprintManager.hasEnrolledFingerprints()) {
                mParaLabel.setText("Register at least one fingerprint in Settings");
            }else{
                if (!mFingerprintManager.isHardwareDetected()) {
                    mParaLabel.setText("Your device does not have a fingerprint sensor");
                }else{
                    mParaLabel.setText("Your Fingerprint can be used to login");

                    mFingerprintHandler.startAuth(mFingerprintManager, null);
                }
            }
        }
    }
}