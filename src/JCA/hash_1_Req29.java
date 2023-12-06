import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_1_Req29 {
    public static void main(String[] args) {
        String text = "This is a test string";

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(
                    text.getBytes(StandardCharsets.UTF_8));

            System.out.println(bytesToHex(hash)); //prints the hash
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);

            if (hex.length() == 1) {
                hexString.append('0');
            }

            hexString.append(hex);
        }

        return hexString.toString();
    }
}