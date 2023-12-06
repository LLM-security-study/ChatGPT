import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class hash_3_Req12 {
    public static void main(String[] args) {
        // Accept data from user
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter data to hash: ");
        String dataToHash = sc.nextLine();

        try {
            // Create a MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password to digest
            md.update(dataToHash.getBytes());

            // Generate the digest
            byte[] digest = md.digest();

            // Converting the byte array in to HexString format
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            System.out.println("Hex format : " + sb.toString());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        sc.close();
    }
}