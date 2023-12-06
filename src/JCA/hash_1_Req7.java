import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_1_Req7 {
    public static String calculateHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String input = "Hello, world!";
        String hash = calculateHash(input);
        System.out.println("The hash of '" + input + "' is:");
        System.out.println(hash);
    }
}