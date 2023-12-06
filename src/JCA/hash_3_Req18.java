import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_3_Req18 {
    public static void main(String[] args) {
        String text = "Hello, World!";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Add password bytes to digest
            md.update(text.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This will convert the bytes to hex
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            System.out.println("Hashed Text: " + sb.toString());

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
    }
}