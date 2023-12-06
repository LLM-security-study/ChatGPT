import java.io.*;
import java.security.*;

public class hash_2_Req6 {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        // Specify the path of the file and the provided hash value
        String filePath = "path/to/file.txt";
        String providedHash = "providedHashValue";

        String calculatedHash = calculateMD5(filePath);

        System.out.println("Calculated MD5 Hash: " + calculatedHash);
        System.out.println("Provided MD5 Hash: " + providedHash);

        if (providedHash.equals(calculatedHash)) {
            System.out.println("The file has not been modified.");
        } else {
            System.out.println("The file has been modified.");
        }
    }

    private static String calculateMD5(String filePath) throws IOException, NoSuchAlgorithmException {
        byte[] buffer = new byte[8192];
        int numOfBytesRead;
        MessageDigest digest = MessageDigest.getInstance("MD5");
        InputStream is = new FileInputStream(filePath);
        while ((numOfBytesRead = is.read(buffer)) > 0) {
            digest.update(buffer, 0, numOfBytesRead);
        }
        byte[] md5Bytes = digest.digest();

        // Convert the byte array into Hex String
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < md5Bytes.length; i++) {
            sb.append(Integer.toString((md5Bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}