import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class integrity_check_2_Req30 {
  
    private static final String PACKAGE_NAME = "com.yourpackage.yourapp";

    public static void main(String[] args) {
        try {
            PackageManager pm = getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(PACKAGE_NAME, PackageManager.GET_SIGNATURES);
            Signature[] signatures = packageInfo.signatures;
            byte[] cert = signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            String hexString = byte2HexFormatted(publicKey);
            String encodedKey = Base64.encodeToString(publicKey, Base64.DEFAULT);
            System.out.println("Key: " + hexString);
            System.out.println("Encoded Key: " + encodedKey);
        } catch (NameNotFoundException e1) {
            e1.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String byte2HexFormatted(byte[] arr) {
        StringBuilder str = new StringBuilder(arr.length * 2);
        for (int i = 0; i < arr.length; i++) {
            String h = Integer.toHexString(arr[i]);
            int l = h.length();
            if (l == 1) h = "0" + h;
            if (l > 2) h = h.substring(l - 2, l);
            str.append(h.toUpperCase());
            if (i < (arr.length - 1)) str.append(':');
        }
        return str.toString();
    }
}