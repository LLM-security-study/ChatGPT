import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_2_Req16 {

    private static String calculateHash(String filepath) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            DigestInputStream dis = new DigestInputStream(new FileInputStream(filepath), md);
            byte[] buffer = new byte[1024];

            // Read the file
            while(dis.read(buffer) != -1) {
                md = dis.getMessageDigest();
            }

            // Get the hash value
            byte[] hash = md.digest();

            // Convert the byte array into signum representation
            StringBuffer sb = new StringBuffer();
            for(int i=0; i<hash.length; i++) {
                sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            return sb.toString();

        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("Usage: java hash_2_Req16 <filename> <hash to check>");
            System.exit(1);
        }

        String computedHash = calculateHash(args[0]);

        if(computedHash == null) {
            System.out.println("Error: could not compute the hash value of the file.");
        } else if(computedHash.equals(args[1])) {
            System.out.println("The hash value is correct. File was not modified.");
        } else {
            System.out.println("The hash value is incorrect. File may have been modified.");
        }
    }
}