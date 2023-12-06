import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_1_Req6 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String originalString = "test string";

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));

        String hashValue = bytesToHex(encodedhash);
        System.out.println(hashValue );
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}