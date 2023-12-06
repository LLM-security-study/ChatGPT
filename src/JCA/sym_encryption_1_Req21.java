import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_1_Req21 {

    private static String SECRET_KEY = "ThisIsASecretKye"; // AES requires 16 bytes key

    public static String encrypt(String strToEncrypt) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static void main(String[] args) {

        String originalString = "Hello, World!";
        
        String encryptedString = sym_encryption_1_Req21.encrypt(originalString);
        String decryptedString = sym_encryption_1_Req21.decrypt(encryptedString);
        
        System.out.println(originalString); // prints: Hello, World!
        System.out.println(encryptedString); // encrypted value
        System.out.println(decryptedString); // prints: Hello, World!, should be same as originalString
    }
}