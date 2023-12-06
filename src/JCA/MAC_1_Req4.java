import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class MAC_1_Req4 {
    public static void main(String[] args) {
        // Create Scanner object for user input
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a message: ");
        String message = sc.nextLine();
        
        System.out.println("Enter a key: ");
        String key = sc.nextLine(); 
        
        System.out.println(generateMac(message, key));
    }

    // Function to generate MAC from message and key
    private static String generateMac(String message, String key) {
        try {
            // Retrive MAC instance with HmacSHA256
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

            // Create SecretKeySpec
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

            // Initialize the MAC with the given key
            sha256_HMAC.init(secret_key);

            // Compute the HMAC on input data bytes
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));

            // Convert byte array into signum representation
            StringBuilder hash = new StringBuilder();
            for (byte aByte : bytes) {
                String hex = Integer.toHexString(0xff & aByte);
                if (hex.length() == 1) hash.append('0');
                hash.append(hex);
            }

            // Get complete hashed password in hexadecimal format
            return hash.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}