import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

class hash_3_Req20 {
    static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                  "Hello, World!".getBytes(StandardCharsets.UTF_8));
            System.out.println(bytesToHex(encodedhash));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error creating MessageDigest: " + e.getMessage());
        }
    }
}