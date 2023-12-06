import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_3_Req4 {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String original = "This is the test string for message digest";
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(original.getBytes("UTF-8"));
        printHash(hash);
    }

    public static void printHash(byte[] hash) {
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        System.out.println(hexString.toString());
    }
}