import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class hash_1_Req10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String inputString = scanner.nextLine();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(inputString.getBytes());
            StringBuilder sb = new StringBuilder();

            for(byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            System.out.println("The hash value of the input string is: " + sb.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}