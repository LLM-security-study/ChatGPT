import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class integrity_check_2_Req17 {
    private static final String PACKAGE_NAME_OF_APPLICATION = "PUT_TARGET_APP_PACKAGE_NAME_HERE";

    public static void main(String[] args) {
        try {
            verifyAppIntegrity();
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            System.out.println("Exception Occurred: " + e.toString());
        }
    }

    public static void verifyAppIntegrity() throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
        //Get installed package info
        PackageManager pm = getPackageManager();
        PackageInfo packageInfo = pm.getPackageInfo(PACKAGE_NAME_OF_APPLICATION, PackageManager.GET_SIGNATURES);
    
        MessageDigest md = MessageDigest.getInstance("SHA");
        for (Signature signature : packageInfo.signatures) {
            md.update(signature.toByteArray());
            String signatureBase64 = new String(Base64.encode(md.digest(), Base64.DEFAULT));
            System.out.println("Signature: " + signatureBase64);
        }
    }
}