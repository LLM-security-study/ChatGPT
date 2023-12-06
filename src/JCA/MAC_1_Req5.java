import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MAC_1_Req5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a message: ");
        String message = scanner.nextLine();
        
        // secret key same for a session, This is just for demo, use a secret key from the secure source.
        String key = "secret";
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] macHash = mac.doFinal(message.getBytes());
            System.out.println(bytesToHex(macHash));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    // method to convert byte array to hexadecimal
    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}