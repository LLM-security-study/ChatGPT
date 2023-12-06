import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class MAC_1_Req15 {
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter a message: ");
            String message = input.nextLine();

            // Generate secret key
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGen.generateKey();
            
            // Convert secret key into string
            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
            
            // Rebuild secretkey using SecretKeySpec
            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");

            // Create a Mac and initialize it with the above secret key
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(originalKey);

            // Perform the Mac
            byte[] macbytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

            // Convert the macbytes to hex string
            System.out.println(bytesToHex(macbytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String r = Integer.toHexString(b & 0xFF);
            if (r.length() == 1) {
                hexString.append('0');
            }
            hexString.append(r);
        }
        return hexString.toString();
    }
}