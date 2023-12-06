import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_3_Req22 {

    private static String decrypt(String strToDecrypt, String secret) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

   public static void main(String args[]) {
       final String secretKey = "myKey";
       String originalString = "qwrtyuioasdfghjklzxcvbnm123456";
       String encryptedString = Base64.getEncoder().encodeToString(originalString.getBytes());
       String decryptedString = decrypt(encryptedString, secretKey) ;

       System.out.println("Original String: " + originalString);
       System.out.println("Encrypted String: " + encryptedString);
       System.out.println("Decrypted String: " + decryptedString);
   }
}