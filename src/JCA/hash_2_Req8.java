import java.io.*;
import java.nio.file.*;
import java.security.*;

public class hash_2_Req8 {   
    public static void main(String[] args) {
        String filePath = "test.txt"; // Update this to path of your file
        String originalHash = "abc123"; // Update this with original hash
        try {
            MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
            String fileHash = getFileChecksum(sha256Digest, filePath);
            System.out.println("Calculated Hash: " + fileHash);
            System.out.println("Original Hash  : " + originalHash);
            System.out.println("Match: " + originalHash.equalsIgnoreCase(fileHash));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String getFileChecksum(MessageDigest digest, String filePath) throws IOException {
        // Read file content
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));

        // Pass a file content to digest
        byte[] hashed = digest.digest(bytes);

        // Convert hashed bytes to hexadecimal
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< hashed.length ;i++) {
            sb.append(Integer.toString((hashed[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}