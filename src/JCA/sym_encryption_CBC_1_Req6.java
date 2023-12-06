import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class sym_encryption_CBC_1_Req6 {
    private static final String keyString = "replaceThisKey!!"; // 16 characters
    private static final String ivString = "replaceThisIV!!!"; // 16 characters

    public static String encrypt(String rawData) throws GeneralSecurityException {
        SecretKeySpec keySpec = new SecretKeySpec(keyString.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ivString.getBytes());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        byte[] encryptedData = cipher.doFinal(rawData.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedData) throws GeneralSecurityException {
        SecretKeySpec keySpec = new SecretKeySpec(keyString.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ivString.getBytes());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedVal = cipher.doFinal(decodedValue);
        return new String(decryptedVal);
    }

    public static void main(String[] args) {
        try {
            String rawData = "Hello, World!";
            String encrypted = encrypt(rawData);
            String decrypted = decrypt(encrypted);
            
            System.out.println("Original: " + rawData);
            System.out.println("Encrypted: " + encrypted);
            System.out.println("Decrypted: " + decrypted);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}