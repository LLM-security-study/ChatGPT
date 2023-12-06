import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class hash_2_Req20 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide a file path and the expected hash as arguments");
            return;
        }

        String filePath = args[0];
        String expectedHash = args[1];
        
        try {
            // Calculate the hash value of the file
            String fileHash = calculateFileHash(filePath);
            
            // Compare the calculated hash with the provided hash
            if(fileHash !=null && fileHash.equals(expectedHash)){
                System.out.println("The file is original.");
            }else{
                System.out.println("The file has been modified.");
            }

        } catch (NoSuchAlgorithmException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Method to calculate hash value of a file
     *
     * @param filePath  the file for which to calculate the hash value
     * @return          the hash value of the file
     */
    private static String calculateFileHash(String filePath) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        DigestInputStream dis = new DigestInputStream(new FileInputStream(filePath), md);
        while (dis.read() != -1) ; //empty loop to clear the data
        md = dis.getMessageDigest();
        byte[] digest = md.digest();

        String fileHash = DatatypeConverter.printHexBinary(digest);
        return fileHash;
    }
}