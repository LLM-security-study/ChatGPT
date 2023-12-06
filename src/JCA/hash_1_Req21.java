import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class hash_1_Req21 {
    public static String calculateHash(String text) {
        try {
            // Create a SHA-256 digest
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(text.getBytes("UTF-8"));
            
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < encodedhash.length; i++) {
                String hex = Integer.toHexString(0xff & encodedhash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:");
        String text = scanner.nextLine();
        
        String hash = calculateHash(text);
        System.out.println("The SHA-256 hash of " + text + " is: " + hash);
    }
}