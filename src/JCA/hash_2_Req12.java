import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class hash_2_Req12 {

    public static String getFileChecksum(MessageDigest digest, File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        DigestInputStream dis = new DigestInputStream(fis, digest);

        byte[] buffer = new byte[1024];
        while (dis.read(buffer) != -1);
        dis.close();

        byte[] bytes = digest.digest();

        BigInteger bi = new BigInteger(1, bytes);
        String fileHash = bi.toString(16);
        while (fileHash.length() < 32) {
            fileHash = "0" + fileHash;
        }

        return fileHash;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java hash_2_Req12 <file_path> <expected_hash>");
            System.exit(1);
        }

        String filePath = args[0];
        String expectedHash = args[1];

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found.");
            System.exit(1);
        }
        
        try {
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            String fileHash = getFileChecksum(md5Digest, file);

            boolean matches = fileHash.equalsIgnoreCase(expectedHash);
            if (matches) {
                System.out.println("File integrity check passed.");
            } else {
                System.out.println("File integrity check failed.");
            }
        } catch (NoSuchAlgorithmException | IOException ex) {
            System.out.println("An error occurred during the file integrity check.");
            ex.printStackTrace();
        }
    }
}