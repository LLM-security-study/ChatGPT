import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_2_Req27 {

    public static void main(String[] args) {
        // Supply the filepath as a command line argument
        String fileName = args[0];
        String originalHash = args[1];  // Original hash for comparison
        try {
            String fileHash = calculateHash(fileName);
            System.out.println(fileHash);

            if(originalHash.equals(fileHash)) {
                System.out.println("File is intact");
            }else {
                System.out.println("File has been modified");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String calculateHash(String file) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(file), md)) {
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