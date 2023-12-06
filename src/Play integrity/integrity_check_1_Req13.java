import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.content.Context;
import android.content.pm.PackageInfo;

public class integrity_check_1_Req13 extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            try {
                PackageManager packageManager = this.getPackageManager();
                String installerPackageName = packageManager.getInstallerPackageName(getPackageName());

                if("com.android.vending".equals(installerPackageName)){
                    Log.d("APP_INSTALLER_CHECK","App installed via Google Play");
                }
                else {
                    Log.d("APP_INSTALLER_CHECK","App was not installed via Google Play");
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
}