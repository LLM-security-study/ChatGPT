import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_3_Req4 {

    private static final String secretKey = "1234567890123456";

    public static void main(String[] args) throws Exception {
        String originalString = "This is a test string for encryption!";

        // Encrypt the string 
        String encryptedString = encrypt(originalString, secretKey) ;
       
        // Decrypt the string
        String decryptedString = decrypt(encryptedString, secretKey) ;
       
        System.out.println("Original String: " + originalString);
        System.out.println("Encrypted String: " + encryptedString);
        System.out.println("Decrypted String: " + decryptedString);
    }

    public static String encrypt(String strToEncrypt, String secret)
    {
        try
        {          
            byte[] key = secret.getBytes(StandardCharsets.UTF_8);

            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

   public static String decrypt(String strToDecrypt, String secret) {
       try
       {
           byte[] key = secret.getBytes(StandardCharsets.UTF_8);

           SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
           Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
           cipher.init(Cipher.DECRYPT_MODE, secretKey);

           return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
       }
       catch (Exception e)
       {
           System.out.println("Error while decrypting: " + e.toString());
       }
       return null;
   }
}