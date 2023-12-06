import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.io.Console;
import java.security.spec.KeySpec;

public class sym_encryption_2_Req29 {
    private static final String secretKey = "secret!";

    public static SecretKey getAESKey() throws Exception {
        char[] password = secretKey.toCharArray();
        byte[] salt = new byte[8]; 
        
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec(password, Arrays.copyOf(salt, salt.length), 65536, 128);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        return secret;
    }

    public static String encrypt(String plainText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return new String(cipher.doFinal(plainText.getBytes("UTF-8")));
    }

    public static String decrypt(String encryptedText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(encryptedText.getBytes()), "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        Console console = System.console();
        if(console == null) {
            System.out.println("Couldn't get Console instance");
            System.exit(0);
        }
        
        String plainText = console.readLine("Enter the text: ");
        SecretKey aesKey = getAESKey();
        String encryptedText = encrypt(plainText, aesKey);
        System.out.println("Encrypted Text : " + encryptedText);
        String decryptedText = decrypt(encryptedText, aesKey);
        System.out.println("Decrypted Text : " + decryptedText);
    }
}