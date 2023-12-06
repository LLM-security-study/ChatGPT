import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.util.Scanner;
import java.security.NoSuchAlgorithmException;

public class MAC_2_Req21 {

    public static String computeMAC(String msg, String keyString) {
        try {
            // Creates a Mac instance with the HMAC-SHA256 algorithm
            Mac mac = Mac.getInstance("HmacSHA256");
            
            SecretKeySpec key = new SecretKeySpec(keyString.getBytes(), "HmacSHA256");
            mac.init(key);

            byte[] bytes = mac.doFinal(msg.getBytes());

            // Converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder();
            for(byte b : bytes) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the message :");
        String msg = scan.nextLine();

        System.out.println("Enter the secret key :");
        String keyString = scan.nextLine();

        String macCode = computeMAC(msg, keyString);
        System.out.println("MAC :" + macCode);

        scan.close();
    }
}