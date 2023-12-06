import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_2_Req7 {
    public static void main(String[] args) {
        // path to the file
        String filePath = "/path/to/file";
        // hash value to be compared with
        String originalHashValue = "original-hash-value";
        
        try {
            // calculating the hash value of the file
            String calculatedHashValue = calculateHashValue(filePath);
            
            // comparing the original and calculated hash values
            if (originalHashValue.equals(calculatedHashValue)) {
                System.out.println("The file has not been modified.");
            } else {
                System.out.println("The file has been modified.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String calculateHashValue(String filePath) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(filePath), md)) {
            while (dis.read() != -1) ; //empty loop to clear the data
            md = dis.getMessageDigest();
        }

        // converting byte array to hex
        StringBuilder result = new StringBuilder();
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b));
        }
        
        return result.toString();
    }
}