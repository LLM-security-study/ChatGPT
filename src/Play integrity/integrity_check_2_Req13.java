import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class integrity_check_2_Req13 {

    private static final String GOOGLE_PLAY_STORE_PACKAGE_NAME = "com.android.vending";

    public static void main(String[] args) {
        Context context = getApplicationContext();
        boolean isRealApp = isAppInstalledFromPlayStore(context);
        System.out.println(isRealApp ? "The app is installed from Google Play Store" : "The app is not installed from the Google Play Store");
    }

    private static boolean isAppInstalledFromPlayStore(Context context) {
        String installer = context.getPackageManager().getInstallerPackageName(context.getPackageName());
        return GOOGLE_PLAY_STORE_PACKAGE_NAME.equals(installer);
    }

    private static Context getApplicationContext() {
        // This method is supposed to be replaced with actual Android application context.
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}