import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_3_Req19 {
    public static String getDigest(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            byte[] messageDigest = md.digest(input.getBytes());
            
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< messageDigest.length ;i++) {
                sb.append(Integer.toString((messageDigest[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
  
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String input = "Welcome to AI Programming";
        String hash = getDigest(input);
        System.out.println("Hash value for \"" + input + "\" is: " + hash);
    }
}