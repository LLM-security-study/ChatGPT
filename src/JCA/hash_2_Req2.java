import java.io.InputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Arrays;

public class hash_2_Req2 {

    private static String givenHash = "Your_Predefined_Hash_Value";   // define your known hash value here

    public static void main(String[] args) {
        String filename = "/path/to/your/file";   // put full path of your file here
        try {
            String calculatedHash = calculateHash(filename);
            System.out.println("Calculated Hash: " + calculatedHash);
            if(calculatedHash.equals(givenHash)) {
                System.out.println("The file is original.");
            } else {
                System.out.println("The file has been modified.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static String calculateHash(String filename) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try (InputStream is = new FileInputStream(filename)) {
            byte[] buffer = new byte[1024];
            int readBytes;
            while ((readBytes = is.read(buffer)) != -1) {
                md.update(buffer, 0, readBytes);
            }
        }
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}