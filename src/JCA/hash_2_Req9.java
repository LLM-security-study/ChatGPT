import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_2_Req9 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java hash_2_Req9 <filepath>");
        } else {
            String filePath = args[0];
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");

                try (DigestInputStream dis = new DigestInputStream(new FileInputStream(filePath), md)) {
                    while (dis.read() != -1) ;   //empty loop to clear the data
                    md = dis.getMessageDigest();
                }

                //convert the byte to hex format
                StringBuilder result = new StringBuilder();
                for (byte b : md.digest()) {
                    result.append(String.format("%02x", b));
                }
                System.out.println("Checksum value for the file is : " + result);
            } catch (NoSuchAlgorithmException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}