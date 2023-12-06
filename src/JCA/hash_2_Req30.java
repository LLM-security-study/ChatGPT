import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class hash_2_Req30 {
    public static void main(String[] args) {
        String filePath = "path/to/your/file";
        String providedHash = "provided_hash_value";

        try {
            String calculatedHash = calculateFileHash(filePath);
            checkFileIntegrity(providedHash, calculatedHash);
        } catch (IOException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to calculate the hash of a file
    private static String calculateFileHash(String filePath) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(filePath), md)) {
            while (dis.read() != -1); //empty read loop to calulate the hash
            md = dis.getMessageDigest();
        }

        // converting the byte array to HexString format
        return DatatypeConverter.printHexBinary(md.digest());
    }

    // Method to check file integrity by comparing the provided and calculated hash values
    private static void checkFileIntegrity(String providedHash, String calculatedHash) {
        if (providedHash.equalsIgnoreCase(calculatedHash)) {
            System.out.println("The file has not been modified.");
        } else {
            System.out.println("The file has been modified or the provided hash is incorrect.");
        }
    }
}