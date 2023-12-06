import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class sym_encryption_2_Req17 {

    private static Cipher cipher;

    public static void main(String[] args) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);

            SecretKey secretKey = keyGenerator.generateKey();
            System.out.println("Secret Key : " + DatatypeConverter.printHexBinary(secretKey.getEncoded()));

            cipher = Cipher.getInstance("AES");

            String plainText = "Hello World!";
            System.out.println("\nPlain Text before encryption: " + plainText);
            
            byte[] cipherText = encrypt(plainText, secretKey);
            System.out.println("Cipher Text after encryption: " + DatatypeConverter.printHexBinary(cipherText));
            
            String decryptedText = decrypt(cipherText, secretKey);
            System.out.println("Plain Text after decryption: " + decryptedText);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    public static byte[] encrypt(String plainText, SecretKey secretKey) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] plainTextByte = plainText.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherText = cipher.doFinal(plainTextByte);
        return cipherText;
    }

    public static String decrypt(byte[] cipherText, SecretKey secretKey) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(cipherText);
        String decryptedText = new String(decryptedBytes);
        return decryptedText;
    }
}