import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_1_Req20
{
    private static final String secretKey = "ssshhhhhhhhhhh!!!!";
    
    public static String encrypt(String strToEncrypt)
    {
        try
        {
            SecretKeySpec secretKey = new SecretKeySpec(getSecretKey().getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
    
    public static String decrypt(String strToDecrypt) 
    {
        try
        {
            SecretKeySpec secretKey = new SecretKeySpec(getSecretKey().getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    private static String getSecretKey() {
        return secretKey;
    }
}