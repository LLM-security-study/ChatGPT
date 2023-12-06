import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class integrity_check_2_Req14 {
    private static final String TAG = "AppIntegrityCheck";
    private static final String TARGET_APP_PACKAGE = "com.example.targetapp";
    private static final String TARGET_APP_SIGNATURE = "308201dd30820146...EXAMPLE...bc5f9f60a";

    public static void main(String[] args) {
        // Assume Context (e.g.: Activity or Application context)
        // is accessible via getContext() function
        Context context = getContext();

        final PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(TARGET_APP_PACKAGE, PackageManager.GET_SIGNATURES);

            // Verify package name
            if (!packageInfo.packageName.equals(TARGET_APP_PACKAGE)) {
                Log.e(TAG, "Package name mismatch");
                return;
            }

            // Verify app signature
            Signature[] appSignatures = packageInfo.signatures;
            if (appSignatures.length == 0) {
                Log.e(TAG, "No signatures found");
                return;
            }

            MessageDigest md;
            md = MessageDigest.getInstance("SHA");
            md.update(appSignatures[0].toByteArray());

            byte[] digest = md.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            if (!hexString.toString().equals(TARGET_APP_SIGNATURE)) {
                Log.e(TAG, "Signature mismatch");
            } else {
                Log.i(TAG, "App integrity check passed");
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Package not found", e);
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "SHA MessageDigest not available", e);
        }
    }
}