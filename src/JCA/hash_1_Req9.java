import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class hash_1_Req9 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the string:");
        String originalString = input.nextLine();

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
            String hash = bytesToHex(encodedhash);
            System.out.println("The hash value of input string is: " + hash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm exception");
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}