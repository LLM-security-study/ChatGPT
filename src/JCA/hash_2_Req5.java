import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class hash_2_Req5 {
    public static void main(String[] args) {
        String filePath = "file.txt";  // Replace this with your actual file path
        String givenHash = "abc123";  // Replace this with the given hash that you want to be compared
        
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
            byte[] fileHashBytes = MessageDigest.getInstance("SHA-256").digest(fileBytes);
            String fileHash = bytesToHex(fileHashBytes);
            
            System.out.println("File's hash: " + fileHash);
            System.out.println("Given hash: " + givenHash);
            
            if(fileHash.equals(givenHash)) {
                System.out.println("File integrity check passed. The file has not been modified.");
            } else {
                System.out.println("File integrity check failed. The file has been modified.");
            }
            
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}