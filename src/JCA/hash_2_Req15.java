import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;

public class hash_2_Req15 {
  public static void main(String[] args) throws Exception {
    // Set these variables to your file and hash
    Path file = Paths.get("path_to_your_file");
    byte[] expectedHash = new byte[] {-85, -85, -85}; // Your hash here

    MessageDigest md = MessageDigest.getInstance("SHA-256");

    byte[] fileBytes = Files.readAllBytes(file);

    byte[] hashBytes = md.digest(fileBytes);

    if (Arrays.equals(expectedHash, hashBytes)) {
      System.out.println("File is intact.");
    } else {
      System.out.println("File was modified.");
    }
  }
}