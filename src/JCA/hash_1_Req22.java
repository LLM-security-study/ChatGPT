import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_1_Req22 {

    public static void main(String[] args) {

        String originalString = "Hello World";

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
            String hash = bytesToHex(encodedhash);
            System.out.println(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}