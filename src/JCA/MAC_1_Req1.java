import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MAC_1_Req1 {

    public static void main(String[] args) {
    
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the message:");
        String message = scanner.nextLine();

        System.out.println("Enter the secret key:");
        String secret = scanner.nextLine();

        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            byte[] rawHmac = sha256_HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : rawHmac) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
            }

            System.out.println("The MAC is: " + sb);

        } catch (Exception e){
            System.out.println("Error generating HMAC: " + e.getMessage());
        }
    }
}