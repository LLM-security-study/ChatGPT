import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class hash_2_Req18 {
    public static void main(String[] args) {
        String fileName = "/path/to/my/input/file.txt";
        String originalHash = "Expected SHA-256 hash to verify";

        try {
            boolean isSame = checkFileIntegrity(fileName, originalHash);
            System.out.println("File integrity check: " + (isSame ? "Unchanged" : "Modified"));
        } catch (IOException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    private static boolean checkFileIntegrity(String fileName, String originalHash) throws IOException, NoSuchAlgorithmException {
        MessageDigest shaDigest = MessageDigest.getInstance("SHA-256");
        FileInputStream fis = new FileInputStream(fileName);

        byte[] buffer = new byte[1024];
        DigestInputStream dis = new DigestInputStream(fis, shaDigest);
        while (dis.read(buffer) != -1);
        dis.close();

        byte[] fileHash = shaDigest.digest();
        return originalHash.equals(bytesToHex(fileHash));
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (byte hashByte : hash) {
            String hex = Integer.toHexString(0xff & hashByte);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}