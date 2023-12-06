import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_1_Req27 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter string: ");
        String input = scanner.nextLine();

        try {
            // Create a SHA-1 instance of MessageDigest
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            
            // Process the string
            md.update(input.getBytes());
            
            // Get the hash's bytes
            byte[] bytes = md.digest();

            // Convert to hexadecimal
            StringBuilder builder = new StringBuilder();
            for (byte b : bytes) {
                builder.append(String.format("%02x", b));
            }
            
            // print the hashed value
            System.out.println("Hash value: " + builder.toString());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}