import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MAC_3_Req21 {
    public static void main(String[] args) {
        String message = "Hello, World!";
        System.out.println("Original Message: " + message);
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // Using SHA-256 hash algorithm
            byte[] messageHash = md.digest(message.getBytes());
            BigInteger no = new BigInteger(1, messageHash);
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            System.out.println("Message Authentication Code (MAC): " + hashtext);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}