import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;

public class hash_2_Req26 {

    private static String calculateHash(String filePath) throws Exception {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
        byte[] fileHash = sha256.digest(fileBytes);
        
        // Convert byte array into signum representation
        StringBuilder result = new StringBuilder();
        for (byte b : fileHash) {
            result.append(String.format("%02x", b));
        }
        
        return result.toString();
    }

    public static boolean isFileIntegrityhash_2_Req26tained(String filePath, String originalHash) {
        try {
            String newHash = calculateHash(filePath);
            if (newHash.equalsIgnoreCase(originalHash)) {
                return true;
            }
        } catch (Exception ex) {
            System.err.println("Error calculating hash: " + ex);
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        String filePath = "/path/to/file";
        String originalHash = "original_sha256_hash";
        if (isFileIntegrityhash_2_Req26tained(filePath, originalHash)) {
            System.out.println("The file's integrity is maintained");
        } else {
            System.out.println("The file has been modified");
        }
    }
}