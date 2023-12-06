import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.google.android.vending.licensing.AESObfuscator;
import com.google.android.vending.licensing.LicenseChecker;
import com.google.android.vending.licensing.LicenseCheckerCallback;
import com.google.android.vending.licensing.ServerManagedPolicy;

public class integrity_check_1_Req23 extends Activity {

    private LicenseCheckerCallback mLicenseCheckerCallback;
    private LicenseChecker mChecker;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // set your activity layout

        String deviceId = Secure.getString(getContentResolver(), Secure.ANDROID_ID);

        mLicenseCheckerCallback = new MyLicenseCheckerCallback();

        byte[] SALT = new byte[]{1, 42, -12, -1, 54, 98, -100, -12, 43, 2, -6, 73, 6, 64, -11, -32, -8, 9, 5, -36};
        mChecker = new LicenseChecker(getApplicationContext(), 
                new ServerManagedPolicy(this, new AESObfuscator(SALT, getPackageName(), deviceId)), 
                "YOUR BASE64 PUBLIC KEY");  

        mChecker.checkAccess(mLicenseCheckerCallback);
    }

    private class MyLicenseCheckerCallback implements LicenseCheckerCallback {

        public void allow(int reason) {
            if (isFinishing()) return;
            // Should allow user access.
        }

        public void dontAllow(int reason) {
            if (isFinishing()) return;
            // Should not allow access. In most cases, the app should assume
            // the user has access unless it encounters this. If it does,
            // the app should inform the user of their unlicensed ways
            // and then either shut down the app or limit the user to a
            // restricted set of features.
            // In this example, we show a dialog that takes the user to Market.
        }

        public void applicationError(int errorCode) {

        }
    }
}