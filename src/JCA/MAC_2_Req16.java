import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MAC_2_Req16 {
    public static void main(String[] args) throws Exception {
        String secretKey = "SuperSecretKey";
        String message = "Hello, World!";
        
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA256");

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        printByteArray(rawHmac);
    }

    private static void printByteArray(byte[] array){
        for (byte b : array) {
            System.out.print(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        System.out.println();
    }
}