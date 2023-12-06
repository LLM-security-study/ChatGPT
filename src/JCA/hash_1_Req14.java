import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_1_Req14 {
    public static void main(String[] args) {
        String originalString = "Integritycheck";

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    originalString.getBytes(StandardCharsets.UTF_8));
                  
            String hashValue = bytesToHex(encodedhash);
            
            System.out.println("Hash value of the given string: " + hashValue);
            
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm exception occurred.");
            e.printStackTrace();
        }
    }
    
    //Convert byte array into a hexadecimal string
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}