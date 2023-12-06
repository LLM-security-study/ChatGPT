import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_3_Req24 {
    public static void main(String[] args) {
        try {
            // The input string
            String inputData = "some secret message";

            // The Secret Key
            // AES key length is 128,192,256 bits, here we are using 128 bits
            byte[] aesKeyData = "sampleKey1234567".getBytes("UTF8");

            SecretKeySpec aesKey = new SecretKeySpec(aesKeyData, "AES");

            // Use AES algorithm with ECB mode and PKCS5 padding
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            // encryption
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encryptedData = cipher.doFinal(inputData.getBytes("UTF8"));
            System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encryptedData));

            // decryption
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] decryptedData = cipher.doFinal(encryptedData);
            System.out.println("Decrypted: " + new String(decryptedData, "UTF8"));
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}