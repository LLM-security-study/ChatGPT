import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import java.util.Base64;

public class sym_encryption_1_Req25 {
    private static SecretKey secretKey;

    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        secretKey = keyGenerator.generateKey();

        String originalString = "Hello, World!";
        System.out.println("Original String: " + originalString);

        try {
            String encryptedString = encrypt(originalString);
            System.out.println("Encrypted String: " + encryptedString);

            String decryptedString = decrypt(encryptedString);
            System.out.println("Decrypted String: " + decryptedString);
        } catch(Exception ex){
            ex.printStackTrace();

        }
    }

    public static String encrypt(String strToEncrypt) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes()));
    }

    public static String decrypt(String strToDecrypt) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }
}