import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class integrity_check_2_Req24 {

    public static void main(String[] args) {
        Context context; 

        // Obtaining the context in real scenario
        context = getApplicationContext();

        // Assuming application is already installed, Here we are taking 'com.example.app' as package name
        String applicationPackageName = "com.example.app";

        PackageManager packageManager = context.getPackageManager();
        Signature[] applicationSignature;
        String currentAppHash = "";

        try {
            applicationSignature = packageManager.getPackageInfo(applicationPackageName, PackageManager.GET_SIGNATURES).signatures;

            for(Signature signature : applicationSignature){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                currentAppHash = bytesToHex(md.digest());
            }

            //Once we have the app hash, we can store it and compare it in future to make sure application has not been tampered.
            System.out.println("Application Signature : " + currentAppHash);

        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }
}