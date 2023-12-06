import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import java.security.MessageDigest;

public class integrity_check_2_Req1 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final String packageName = "com.example.yourapp";  //replace with the package name of the app you are checking

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            
            // NOTE: If multiple signatures, just pick the first one
            Signature signature = packageInfo.signatures[0];
            MessageDigest md;
            md = MessageDigest.getInstance("SHA");
            md.update(signature.toByteArray());
            String appSignature = toHexString(md.digest());

            // compare signatures
            if (appSignature.equals(yourOriginalSignature)) {
                // it is your app, matches with your signature
            } else {
                // it is NOT your app and may be a repackaged clone
            }
        } catch (Exception e) {
            // handle error
        }
    }

    private static String toHexString(byte[] block) {
        StringBuilder buf = new StringBuilder();
        int len = block.length;
        for (int i = 0; i < len; i++) {
            byte2hex(block[i], buf);
            if (i < len-1) {
                buf.append(":");
            }
        }
        return buf.toString();
    }

    private static void byte2hex(byte b, StringBuilder buf) {
        char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', 
            '9', 'A', 'B', 'C', 'D', 'E','F' };
        int high = ((b & 0xf0) >> 4);
        int low = (b & 0x0f);
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }
}