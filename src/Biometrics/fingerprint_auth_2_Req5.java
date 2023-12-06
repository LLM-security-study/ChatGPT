import android.content.Context;
import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;

public class fingerprint_auth_2_Req5 {

    private CancellationSignal cancellationSignal;
    private FingerprintManager fingerprintManager;

    public fingerprint_auth_2_Req5(Context context) {
        fingerprintManager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
    }

    public void startAuth(FingerprintManager.AuthenticationCallback callback) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fingerprintManager.authenticate(null, cancellationSignal, 0, callback, null);
    }

    public void cancel() {
        cancellationSignal.cancel();
    }
}