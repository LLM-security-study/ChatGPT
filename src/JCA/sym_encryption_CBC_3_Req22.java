import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_CBC_3_Req22 {
    public static void main(String[] args) {
        try
        {
            // Your secret key, stored securely
            String secret = "mySuperSecretKey"; 

            // CipherText written in base64
            String encryptedText = "scDfJZT+89K2xABELYjP/Q==";

            // Initialization Vector, needed for CBC mode
            byte[] iv = new byte[16];

            // Using AES encryption with CBC mode and PKCS5 padding
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "AES");

            // Decryption starts
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

            System.out.println("Decrypted text: " + new String(decrypted));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
    }
}