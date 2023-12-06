import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_1_Req3 {
    public static void main(String[] args) {
        String originalString = "YourStringHere"; //replace with your string

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    originalString.getBytes(StandardCharsets.UTF_8));
            
            String hex = bytesToHex(encodedhash);
            System.out.println("The hash value is: " + hex);

        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error generating hash: " + ex.getMessage());
        }
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