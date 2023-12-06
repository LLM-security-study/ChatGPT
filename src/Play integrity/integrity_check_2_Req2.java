import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class integrity_check_2_Req2 extends Activity {

    private final static String TAG = "AppIntegrity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            checkAppIntegrity();
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            Log.e(TAG, "Error occurred when checking integrity", e);
        }
    }

    private void checkAppIntegrity() throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
        Signature[] signatures = this.getPackageManager()
                .getPackageInfo(this.getPackageName(), PackageManager.GET_SIGNATURES).signatures;
        for (Signature signature : signatures) {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(signature.toByteArray());
            Log.d(TAG, "App Signature: " + Base64.encodeToString(md.digest(), Base64.DEFAULT));
        }
    }
}