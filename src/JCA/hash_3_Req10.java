import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_3_Req10 {
    public static void main(String[] args) {
        String message = "yourMessageHere";

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
            String sha256hex = bytesToHex(encodedhash);
            System.out.println("The SHA-256 hash of '" + message + "' is:");
            System.out.println(sha256hex);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}