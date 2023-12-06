import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class hash_3_Req11 {
    public static void main(String[] args) {
        String originalString = "Hello, World!";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
            String hashedString = bytesToHex(encodedhash);
            System.out.println("Hashed String: " + hashedString);
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