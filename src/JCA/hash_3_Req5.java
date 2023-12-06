import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class hash_3_Req5 {

    public static byte[] createSHA256(String message) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(message.getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String message = "Your Message";
        byte[] hash = createSHA256(message);
        String hashPrintable = DatatypeConverter.printHexBinary(hash);
        System.out.println(hashPrintable);
    }
}