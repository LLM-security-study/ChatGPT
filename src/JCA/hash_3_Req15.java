import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class hash_3_Req15 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter string to hash: ");
        String originalString = input.nextLine();

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
            String hashedString = bytesToHex(encodedhash);
            System.out.println("Hashed string: " + hashedString);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error generating hash: " + e.getMessage());
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}