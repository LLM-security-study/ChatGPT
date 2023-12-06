import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.M)
public class fingerprint_auth_1_Req3 {
  
  FingerprintManager fingerprintManager;

  @RequiresApi(api = Build.VERSION_CODES.M)
  public boolean checkFingerPrintSensor() {
    // Checking if fingerprint sensor exists
    if (!fingerprintManager.isHardwareDetected()) {
      Toast.makeText(this, "Fingerprint authentication permission not enabled",
          Toast.LENGTH_SHORT).show();
      return false;
    }
    // Checking if permission is given to use fingerprint sensor
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
      Toast.makeText(this, "Fingerprint authentication permission not enabled", Toast.LENGTH_SHORT).show();
      return false;
 
    }
    return fingerprintManager.hasEnrolledFingerprints();
  }
}