import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.DatatypeConverter;

public class hash_1_Req24 {
    public static void main(String[] args) {
        String input = "Calculate my hash please";
        System.out.println("Hash value of the input string: " + calculateHash(input));
    }

    public static String calculateHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}