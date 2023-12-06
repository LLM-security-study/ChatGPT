import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_3_Req1 {
    public static void main(String[] args) {
        String original = "Hello, World!";

        try {
           MessageDigest md = MessageDigest.getInstance("SHA-256");
           byte[] digest = md.digest(original.getBytes());
        
           String hashed = toHexString(digest);
        
           System.out.println("Original string: " + original);
           System.out.println("Hashed string: " + hashed);
        
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String toHexString(byte[] hash) {
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}