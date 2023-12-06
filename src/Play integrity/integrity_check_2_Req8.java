import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class integrity_check_2_Req8 {
    public static void main(String[] args) {
        //Assumption: The packageName variable would be initialized with the package name of the app to check
        String packageName = "com.applicationName.yourAppName";
        
        //The context variable would be initialized with the context of the app
        Context context = getApplicationContext();
        
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            
            if (packageInfo != null) {
                System.out.println("Application is Genuine");
            } else {
                System.out.println("Application is not Genuine");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            System.out.println("An error occurred while trying to find the package");
        }
    }
}