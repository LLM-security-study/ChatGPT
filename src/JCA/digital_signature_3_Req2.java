import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class digital_signature_3_Req2 {

    public static String signMessage(String message) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String message = "Hello, World!";
        String signedMessage = signMessage(message);
        System.out.println("Original Message: " + message);
        System.out.println("Signed Message: " + signedMessage);
    }
}