import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class integrity_check_2_Req10 {
    private static final String TAG = "IntegrityCheck";

    //Supply the expected signature
    private static final String EXPECTED_SIGNATURE = "<Expected_Signature>";

    public static void main(String[] args) {
        //Assume the context object as supplied
        PackageManager pm = context.getPackageManager();
            
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            if (packageInfo == null
                || packageInfo.signatures == null
                || packageInfo.signatures.length == 0
                || packageInfo.signatures[0] == null) {

                Log.e(TAG, "No signatures found!");
            } else {
                Signature signature = packageInfo.signatures[0];
                MessageDigest md;
                
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                String currentSignature = Base64.encodeToString(md.digest(), Base64.NO_WRAP);
                
                //Compare signatures 
                if(EXPECTED_SIGNATURE.equals(currentSignature)){
                    Log.i(TAG, "App's signature is valid");
                } else {
                    Log.e(TAG, "App's signature is NOT valid");
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Unable to find package to obtain signature.", e);
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "Unable to obtain the necessary algorithm to compute signature.", e);
        }
    }
}