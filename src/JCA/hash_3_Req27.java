import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.DatatypeConverter;

public class hash_3_Req27 {
    public static void main(String[] args) {
        String message = "This is the string to be hashed.";
        try {
            // Get instance of the SHA-256 Message Digest
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Perform the hashing
            byte[] hashedMessage = md.digest(message.getBytes(StandardCharsets.UTF_8));

            // Convert hashed message from bytes to hexadecimal
            String hashedMessageHex = DatatypeConverter.printHexBinary(hashedMessage);

            System.out.println("Original Message: " + message);
            System.out.println("Hashed Message: " + hashedMessageHex);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}