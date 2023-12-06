import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class hash_1_Req11 {
    public static void main(String[] args) throws Exception {
        System.out.println("Enter a string value: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                
                // Add zero if single-digit hex
                if (hex.length() == 1) hexString.append('0');
                
                hexString.append(hex);
            }

            System.out.println("The hash value of " + input + " is: " + hexString);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}