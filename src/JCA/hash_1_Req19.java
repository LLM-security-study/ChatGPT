import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_1_Req19 {
    public static void main(String[] args) {
        String input = "Hello, world!";
        try {
            // Create a MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Add password bytes to digest
            md.update(input.getBytes());
            // Get the hash's bytes
            byte[] bytes = md.digest();
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hexadecimal format
            String generatedPassword = sb.toString();
            System.out.println("Hashed string is : "+ generatedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}