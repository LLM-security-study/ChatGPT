import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_1_Req28 {
    public static void main(String[] args) {
        String originalString = "Hello World!";
        System.out.println("Hash for "+originalString+" is: "+getSHA256Hash(originalString));
    }

    public static String getSHA256Hash(String input) {
        StringBuilder hash = new StringBuilder();

        try { 
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
    
            for (byte b : encodedhash) {
                hash.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return hash.toString();
    }
}