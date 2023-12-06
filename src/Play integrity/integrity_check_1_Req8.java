import java.security.MessageDigest;
import java.io.FileInputStream;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import android.content.Context;

public class integrity_check_1_Req8 {
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static void main(String[] args) {
        boolean isAppValid = isAppSignatureValid(context, <Your_Should_Hardcode_Expected_Hash_Here>);
        System.out.println("Is App Valid: " + isAppValid);
    }

    public static String calculateSHA256(String filepath) {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        FileInputStream fis = new FileInputStream(filepath);
        byte[] dataBytes = new byte[1024];

        int nread = 0; 
        while ((nread = fis.read(dataBytes)) != -1) {
          md.update(dataBytes, 0, nread);
        };

        byte[] mdbytes = md.digest();
        return bytesToHex(mdbytes);
    }

    public static boolean isAppSignatureValid(Context context, String expectedHash) {
        try {
            ApplicationInfo appInfo = context.getApplicationContext().getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            String appFile = appInfo.sourceDir;
            String calculatedHash = calculateSHA256(appFile);
            return calculatedHash.equals(expectedHash);
        }
        catch (Exception e) {
            Log.e(TAG, "Unable to calculate hash", e);
            return false;
        }
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];

        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }

        return new String(hexChars);
    }
}