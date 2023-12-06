import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class integrity_check_2_Req28 {
  
  private static final String APP_PACKAGE_NAME = "com.example.myapp"; // Replace with your Android app's package name
  private static final String APP_SIGNATURE_HASH = "7cfa60286f6b4ae8d2c33de214a8dac37ab02acf"; // Replace with your Android app's signature hash

  public static void main(String[] args) {
    boolean isAppIntegrityOk = verifyAppIntegrity();

    if(isAppIntegrityOk) {
      System.out.println("App integrity is ok");
    } else {
      System.out.println("App integrity is compromised");
    }
  }

  private static boolean verifyAppIntegrity() {
    try {
      // Get installed app signature hash
      Signature[] signatures = context.getPackageManager().getPackageInfo(APP_PACKAGE_NAME, PackageManager.GET_SIGNATURES).signatures;

      for (Signature signature : signatures) {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA");
        md.update(signature.toByteArray());
        String currentSignatureHash = convertToHex(md.digest());

        // Compare with expected signature hash
        if (APP_SIGNATURE_HASH.equals(currentSignatureHash)) {
          return true;
        }
      }
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return false;
  }

  private static String convertToHex(byte[] data) {
    StringBuilder buf = new StringBuilder();
    for (byte b : data) {
      int halfbyte = (b >>> 4) & 0x0F;
      int two_halfs = 0;
      do {
        if ((0 <= halfbyte) && (halfbyte <= 9)) {
          buf.append((char) ('0' + halfbyte));
        } else {
          buf.append((char) ('a' + (halfbyte - 10)));
        }
        halfbyte = b & 0x0F;
      } while (two_halfs++ < 1);
    }
    return buf.toString();
  }
}