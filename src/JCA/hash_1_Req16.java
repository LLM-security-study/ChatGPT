import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class hash_1_Req16 {
    public static void main(String[] args) {
        // The string whose hash needs to be calculated
        String str = "Hello World";
        
        String sha256Hash = getSHA256Hash(str);
        System.out.println("The SHA-256 Hash of string is: " + sha256Hash);
    }

    public static String getSHA256Hash(String input) {
        try {
            // Static getInstance method is called with hashing SHA
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // digest() method called to calculate message digest
            // of an input digest() return array of byte
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return toHexString(hash);

        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
}