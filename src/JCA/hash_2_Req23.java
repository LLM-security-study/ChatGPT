import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class FileIntegrityChecker {
    private static final String MD5 = "MD5";

    public String calculateMD5(File file) {
        try (DigestInputStream dis = new DigestInputStream(new BufferedInputStream(new FileInputStream(file)), MessageDigest.getInstance(MD5))) {
            while (dis.read() != -1); //empty loop to clear the data
            MessageDigest md = dis.getMessageDigest();
            StringBuilder result = new StringBuilder();
            for (byte b : md.digest()) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
        } catch (IOException | NoSuchAlgorithmException ex) {
            System.out.println("Error occurred while calculating MD5: " + ex.getMessage());
        }
        return null;
    }
}

public class hash_2_Req23 {
    public static void main(String[] args) {
        FileIntegrityChecker fic = new FileIntegrityChecker();
        String providedHash = "abcdef123456"; // Assuming provided hash. This should be provided
        Path path = Paths.get("Path of your file"); //Specify the path of your file
        String myFileHash = fic.calculateMD5(path.toFile());
        if (providedHash.equals(myFileHash)) {
            System.out.println("File integrity confirmed");
        } else {
            System.out.println("File has been modified");
        }
    }
}