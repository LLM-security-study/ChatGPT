import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_1_Req13 {
    public static void main(String[] args) {
        // Test string to calculate hash value
        String test = "Hello, World!";
        
        // call the function with string
        String computedHash = "";
        try {
            computedHash = calculateHash(test);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
        
        // print the computed hash
        System.out.println("The hash value for " + test + " is: " + computedHash);
    }

    private static String calculateHash(String text) throws NoSuchAlgorithmException {

        // Create a SHA-256 message digest instance
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        
        // Get the hash value
        byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
        
        // Convert the byte array into a hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) 
                hexString.append('0');
            hexString.append(hex);
        }
       
        return hexString.toString();
    }
}