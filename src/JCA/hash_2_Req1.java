import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

public class hash_2_Req1 {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: java hash_2_Req1 <file-path> <comparison-hash-value>");
            return;
        }

        String filePath = args[0];
        String comparisonHashValue = args[1];

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        try (InputStream is = Files.newInputStream(Paths.get(filePath));
             DigestInputStream dis = new DigestInputStream(is, md)) {

            byte[] buffer = new byte[1024];
            // Reading file content
            while (dis.read(buffer) != -1)
                ;
            } catch (IOException e) {
            System.err.println("Unable to read file");
            return;
        }

        byte[] digest = md.digest();
        String myFileHash = DatatypeConverter.printHexBinary(digest);
        System.out.println("Calculated hash: " + myFileHash);
        System.out.println("Comparison hash: " + comparisonHashValue);

        boolean integrityCheckResult = comparisonHashValue.equalsIgnoreCase(myFileHash);

        if (integrityCheckResult) {
            System.out.println("The file is not modified. The integrity check is successful.");
        } else {
            System.out.println("The file is modified. The integrity check is failed.");
        }
    }
}