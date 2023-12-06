import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class sym_encryption_2_Req4 {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    public static void setKey(String myKey) 
    {
        key = myKey.getBytes();
        key = Arrays.copyOf(key, 16); // To make sure key length is 16 bytes
        secretKey = new SecretKeySpec(key, "AES");
    }
    
    public static String encrypt(String strToEncrypt, String secret) 
    {
        try 
        {    
            setKey(secret);
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, secretKey);
            return new String(c.doFinal(strToEncrypt.getBytes()));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String secret) 
    {
        try 
        {
            setKey(secret);
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(c.doFinal(strToDecrypt.getBytes()));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static void main(String[] args) {
        
        final String secretKey = "supersecretkeyyy";
     
        String originalString = "This is a secret message";
        String encryptedString = sym_encryption_2_Req4.encrypt(originalString, secretKey) ;
        String decryptedString = sym_encryption_2_Req4.decrypt(encryptedString, secretKey) ;
     
        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
    }
}