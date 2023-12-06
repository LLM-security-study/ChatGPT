import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class hash_1_Req26 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:");
        String input = scanner.nextLine();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // using SHA-256 algorithm

            byte[] hash = md.digest(input.getBytes());

            // Converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder();
            for(byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            System.out.println("Hash of the input string is:");
            System.out.println(sb.toString());

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error occurred while calculating hash:");
            e.printStackTrace();
        }
    }
}