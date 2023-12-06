import java.nio.file.*;
import java.security.*;
import javax.xml.bind.DatatypeConverter;

public class hash_2_Req29 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java hash_2_Req29 [file_path] [expected_hash]");
            return;
        }

        String filePath = args[0];
        String expectedHash = args[1];

        try {
            String fileHash = calculateHash(filePath);
            System.out.println("Calculated file hash is: " + fileHash);
    
            if (fileHash.equals(expectedHash)) {
                System.out.println("The file integrity check passed.");
            } else {
                System.out.println("The file integrity check failed.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String calculateHash(String filePath) throws Exception {
        MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
        byte[] hashedBytes = sha256Digest.digest(fileBytes);
        return DatatypeConverter.printHexBinary(hashedBytes);
    }
}