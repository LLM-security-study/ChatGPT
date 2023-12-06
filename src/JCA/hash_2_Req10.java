import java.security.MessageDigest;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.bind.DatatypeConverter;

public class hash_2_Req10 {
    public static void main(String[] args) {
        try {
            String filePath = args[0];
            String expectedHash = args[1];

            boolean result = isFileIntegrityValid(filePath, expectedHash);

            System.out.println("File integrity check result: " + (result ? "Valid" : "Invalid"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static boolean isFileIntegrityValid(String filePath, String expectedHash) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));

        byte[] fileHashBytes = messageDigest.digest(fileBytes);
        
        // convert the byte to hex format
        String fileHash = toHex(fileHashBytes);

        return expectedHash.equals(fileHash);
    }

    private static String toHex(byte[] bytes) {
        return DatatypeConverter.printHexBinary(bytes).toLowerCase();
    }
}