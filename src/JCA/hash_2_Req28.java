import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_2_Req28 {
    
    public static void main(String[] args) throws Exception {
        String fileName = "example.txt"; // File to check
        String originalHash = "5d41402abc4b2a76b9719d911017c592"; // Original hash

        String calculatedHash = calculateHash(fileName);
        System.out.println("Original Hash: " + originalHash);
        System.out.println("Calculated Hash: " + calculatedHash);
        
        if(calculatedHash.equals(originalHash)) {
            System.out.println("Integrity Check Passed!");
        } else {
            System.out.println("Integrity Check Failed!");
        }
    }
    
    private static String calculateHash(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName); 
             DigestInputStream dis = new DigestInputStream(fis, MessageDigest.getInstance("MD5"))) {
            
            while (dis.read() != -1) { /*empty loop to clear the data input stream*/ }
            
            MessageDigest md = dis.getMessageDigest();
            byte[] bytes = md.digest();
            
            StringBuilder sb = new StringBuilder();
            for(byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            
            return sb.toString();
            
        } catch (NoSuchAlgorithmException | IOException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
}