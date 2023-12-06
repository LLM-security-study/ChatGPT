import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

public class hash_1_Req12 {
    public static String getHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, digest);

            // Convert message digest into hex value
            StringBuilder hashText = new StringBuilder(number.toString(16));

            // Add preceding 0s to make it 32 bit
            while (hashText.length() < 32)
                hashText.insert(0, "0");

            // return the HashText
            return hashText.toString();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String input = "calculate hash of this string";
        String hash = getHash(input);
        
        System.out.println("Hash of '" + input + "' : " + hash);
    }
}