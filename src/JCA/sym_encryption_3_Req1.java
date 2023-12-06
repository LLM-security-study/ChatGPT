import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_3_Req1 {
    public static void main(String[] args) {
        try {
            final String secretKey = "ssshhhhhhhhhhh!!!!";

            String originalString = "Test String";
            String encryptedString = encrypt(originalString, secretKey) ;
            String decryptedString = decrypt(encryptedString, secretKey) ;
        
            System.out.println(originalString);
            System.out.println(encryptedString);
            System.out.println(decryptedString);
        } catch (Exception e) {
            System.out.println("Error while encrypting or decrypting: " + e.toString());
        }
    }

    public static String encrypt(String strToEncrypt, String secret)  {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String secret) {
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
}