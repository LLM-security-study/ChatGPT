import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.M)
public class fingerprint_auth_3_Req4{

    private FingerprintManager fingerprintManager;
    private CancellationSignal cancellationSignal;

    public fingerprint_auth_3_Req4(FingerprintManager fm) {
        this.fingerprintManager = fm;
    }

    public void startAuth() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fingerprintManager.authenticate(null, cancellationSignal, 0, new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                update("Auth Error : " + errString, false);
            }

            @Override
            public void onAuthenticationFailed() {
                update("Auth Failed. ", false);
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                update("Auth Help : " + helpString, false);
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                update("Auth Success! ", true);
            }
        }, null);
    }

    public void update(String e, Boolean success){
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(e);
        if(success){
            textView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }
}