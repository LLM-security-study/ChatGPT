import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

public class integrity_check_1_Req17 {
    private static final String EXPECTED_SIGNATURE = "INSERT_EXPECTED_SIGNATURE_HERE";

    public static void main(String[] args) {
        // Assume that the context is already available
        Context context = ...; 

        try {
            // Get package info for this application
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);

            // Loop through signatures
            for (Signature signature : packageInfo.signatures) {
                // Compute hash of signature
                byte[] signatureBytes = signature.toByteArray();
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signatureBytes);
                String currentSignature = Base64.encodeToString(md.digest(), Base64.DEFAULT);

                // Compare with expected signature
                if(EXPECTED_SIGNATURE.equals(currentSignature.trim())
                {
                    System.out.println("The app's signature is valid.");
                } else {
                    System.out.println("The app's signature is NOT valid.");
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            System.out.println("The package name is not found.");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("The specified algorithm is not available.");
        }
    }
}