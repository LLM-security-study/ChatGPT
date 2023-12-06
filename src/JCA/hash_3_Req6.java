import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_3_Req6 {
    public static void main(String[] args) {
        String message = "Hello World!";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(message.getBytes());
            byte[] digest = md.digest();
            System.out.println("Generated hash: " + bytesToHex(digest));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error occurred: " + e.toString());
        }
    }

    public static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}