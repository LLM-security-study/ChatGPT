import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class hash_1_Req15 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the string:");
        String input = scanner.nextLine();

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(input.getBytes());

        StringBuilder sb = new StringBuilder();
        for(byte b : digest){
            sb.append(String.format("%02x", b & 0xff));
        }

        System.out.println("The hash value of input string is : " + sb.toString());
    }
}