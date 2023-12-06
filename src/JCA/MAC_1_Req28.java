import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MAC_1_Req28 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the message:");
        String message = scanner.nextLine();

        // Secret key - in a real-world application, it should be properly generated and stored
        String secret_key = "THISISASECRETKEY";

        String mac = generateMac(message, secret_key);
        System.out.println("Generated MAC: " + mac);

    }

    public static String generateMac(String msg, String secret) {
        String hash = "";
        try {
            // Get an algorithm instance
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            
            // Create secret key
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            
            // Initialize the Mac instance with the secret key
            sha256_HMAC.init(secret_key);
            
            // Compute the hmac on input data bytes
            byte[] mac_data = sha256_HMAC.doFinal(msg.getBytes(StandardCharsets.UTF_8));
            
            // Convert byte array to hex string
            hash = byteArrayToHexString(mac_data);
            
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return hash;
    }
    
    public static String byteArrayToHexString(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}