import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class hash_3_Req30 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the message:");
        String message = sc.nextLine();

        try {

            // Create an instance of MessageDigest for MD5 hash function
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Run the hash function
            md.update(message.getBytes());

            // Get the resulting hash value
            byte[] digest = md.digest();

            // Convert it into hexadecimal
            StringBuffer hexadecimal = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                hexadecimal.append(Integer.toHexString(0xFF & digest[i]));
            }

            // Print the result
            System.out.println("Hash value is: " + hexadecimal.toString());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}