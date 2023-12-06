import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_CBC_3_Req4
{
    public static void main(String[] args)
    {
        try
        {
            // Initialize the key and iv
            String key = "0123456789012345";
            String initVector = "abcdefghijklmnop";

            // Encrypted data 
            String encryptedData = "Your encrypted data";

            System.out.println("Decrypted data: " + decrypt(key, initVector, encryptedData));
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public static String decrypt(String key, String initVector, String encryptedData) 
    {
        try 
        {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec sKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedData));

            return new String(original);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return null;
    }
}