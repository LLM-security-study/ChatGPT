import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_2_Req14 {
    public static void main(String[] args) {
        String filePath = "path-to-your-file"; // replace with your file path
        String originalHash = "original-file-hash"; // replace with original file hash

        try {
            String computedHash = calculateFileHash(filePath);
            System.out.println("Computed file hash: " + computedHash);
            
            if (originalHash.equals(computedHash)) {
                System.out.println("File integrity confirmed, no alterations.");
            } else {
                System.out.println("File was altered!");
            }
        
        } catch (IOException | NoSuchAlgorithmException e){
            System.out.println("Error! " + e.getMessage());
        }
    }

    public static String calculateFileHash(String filePath) 
        throws IOException, NoSuchAlgorithmException {
    
         MessageDigest md = MessageDigest.getInstance("MD5");
         FileInputStream fis = new FileInputStream(filePath);
         DigestInputStream dis = new DigestInputStream(fis, md);
        
         byte[] buffer = new byte[1024];
         while (dis.read(buffer) != -1) ;
        
         byte[] digest = md.digest();

         fis.close();
         dis.close();

         return String.format("%032x", new BigInteger(1, digest));
    }
}