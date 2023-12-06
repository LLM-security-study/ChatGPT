import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class hash_2_Req13 {
    public static void main(String[] args) {
        try {
            String fileName = "your_file_path";  // Replace with your file path
            String originalHash = "your_original_hash"; // Replace with expected hash value
            
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            try (DigestInputStream dis = new DigestInputStream(new FileInputStream(fileName), md)) {
                while (dis.read() != -1) ; //empty loop to clear the data
                md = dis.getMessageDigest();
            }
            
            // converting byte array to HEX string
            String fileHash = DatatypeConverter.printHexBinary(md.digest());
            
            if(originalHash.equals(fileHash)) {
                System.out.println("File integrity check successful. No modifications detected.");
            } else {
                System.out.println("File integrity check failed. Modifications detected.");
            }

        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}