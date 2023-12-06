import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_2_Req11 {
  
  public static void main(String[] args) {
    
    String fileToCheck = "path_to_your_file";
    String knownHash = "put_the_known_hash_here";

    try {
      String calculatedHash = calculateHash(fileToCheck);
      System.out.println("Calculated hash: " + calculatedHash);

      if (calculatedHash.equals(knownHash)) {
        System.out.println("File is intact.");
      } else {
        System.out.println("File has been modified.");
      }
    } catch (IOException | NoSuchAlgorithmException e) {
      System.out.println("An error occurred while checking the file's integrity.");
      e.printStackTrace();
    }
  }

  public static String calculateHash(String filePath) throws IOException, NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    try (DigestInputStream dis = new DigestInputStream(new FileInputStream(filePath), md)) {
      byte[] buffer = new byte[1024];
      while (dis.read(buffer) != -1) {}
      md = dis.getMessageDigest();
    }

    BigInteger bigInt = new BigInteger(1, md.digest());
    String output = bigInt.toString(16);
    
    while (output.length() < 32) {
      output = "0" + output;
    }

    return output;
  }
}