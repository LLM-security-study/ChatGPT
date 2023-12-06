import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class hash_2_Req4 {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java hash_2_Req4 <file> <expected-hash>");
            System.exit(1);
        }
        String filePath = args[0];
        String expectedHash = args[1];

        try {
            String actualHash = calculateHash(filePath);

            if (expectedHash.equals(actualHash)) {
                System.out.println("File integrity check passed");
            } else {
                System.out.println("File integrity check failed");
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println("Error occurred during file integrity check: " + e.getMessage());
            System.exit(1);
        }
    }

    public static String calculateHash(String filePath) throws IOException, NoSuchAlgorithmException {
        Path path = Paths.get(filePath);

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        DigestInputStream dis = new DigestInputStream(Files.newInputStream(path), md);

        while (dis.read() != -1); //empty loop to clear the data
        dis.close();

        byte[] digest = md.digest();
        
        // Convert the byte to hex format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
    }
}