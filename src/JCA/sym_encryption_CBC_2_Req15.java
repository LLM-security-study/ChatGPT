import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;

public class sym_encryption_CBC_2_Req15 {
    public static void main(String[] args) throws Exception {
        // Generate a new AES key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();

        // Generate a new initialization vector (IV)
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Initialize the cipher for encryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

        // Encrypt the plaintext
        String plaintext = "This is a test.";
        byte[] ciphertext = cipher.doFinal(plaintext.getBytes());

        // Print the ciphertext
        System.out.println(new String(ciphertext));
    }
}