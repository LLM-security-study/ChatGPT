import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_2_Req24 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java hash_2_Req24 <file path> <expected hash value>");
            System.exit(1);
        }
        String filePath = args[0];
        String expectedHashValue = args[1];

        try {
            String calculatedHashValue = calculateHash(filePath);
            System.out.println("Calculated Hash: " + calculatedHashValue);
            System.out.println("Expected Hash: " + expectedHashValue);

            if (calculatedHashValue.equals(expectedHashValue)) {
                System.out.println("Integrity Check Passed!");
            } else {
                System.out.println("Integrity Check Failed!");
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println("Error calculating hash of file: " + e.getMessage());
        }
    }

    private static String calculateHash(String filePath) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(filePath), md)) {
            while (dis.read() != -1) ; 
            md = dis.getMessageDigest();
        }

        StringBuilder result = new StringBuilder();
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}