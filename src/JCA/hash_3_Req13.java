import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

// Your class should always start with a capital letter
public class hash_3_Req13 {
  
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String originalString = "text to hash";

        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] encodedhash = digest.digest(
            originalString.getBytes(StandardCharsets.UTF_8));

        System.out.println(bytesToHex(encodedhash));  
    }

    /**
     *  byteToHex method to convert a byte array into a hex string.
     *  @param hash The input byte array.
     *  @return Returns the constructed hex string.
     *
     */
    public static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}