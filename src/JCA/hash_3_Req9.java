import java.security.MessageDigest;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class hash_3_Req9 {
    public static void main(String[] args) {
        System.out.println("Enter a string:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            System.out.println("Hash:");
            for (byte b : encodedHash) {
                System.out.format("%02x", b);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error generating hash: " + e.getMessage());
        }
    }
}