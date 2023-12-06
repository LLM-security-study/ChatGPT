import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.DatatypeConverter;

public class hash_3_Req23 {
  
    public static void main(String args[]) {
        String message = "Hello, World!";
        String sha256Hash = getSHA256Hash(message);
        System.out.println("The SHA-256 hash of \"" + message + "\" is:");
        System.out.println(sha256Hash);
    }
  
    private static String getSHA256Hash(String input) {
        String hashValue = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            hashValue = DatatypeConverter.printHexBinary(hash);
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashValue;
    }
}