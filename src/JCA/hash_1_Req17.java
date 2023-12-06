import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_1_Req17 {
    public static void main(String[] args) {
        System.out.println("Enter a string:");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashbytes = digest.digest(inputString.getBytes(StandardCharsets.UTF_8));
            String hashValue = bytesToHex(hashbytes);
            System.out.println("The hash value of the given string is: " + hashValue);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}