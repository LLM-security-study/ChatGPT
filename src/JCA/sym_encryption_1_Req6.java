import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class sym_encryption_1_Req6 {

    // Function to encrypt a string
    public static byte[] encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES"); // Instance of AES Cipher
        cipher.init(Cipher.ENCRYPT_MODE, secretKey); // Initialising cipher with secret key to encrypt
        byte[] cipherText = cipher.doFinal(plainText.getBytes()); // Encrypting the text
        return cipherText;
    }

    // Function to decrypt a string
    public static String decrypt(byte[] cipherText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES"); // Instance of AES Cipher
        cipher.init(Cipher.DECRYPT_MODE, secretKey); // Initialising cipher with secret key to decrypt
        String decryptedText = new String(cipher.doFinal(cipherText)); // Decrypting the text
        return decryptedText;
    }

    public static void main(String[] args) throws Exception {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES"); // Key generator for AES
        keyGenerator.init(128); // Key size is set to 128 bits
        SecretKey secretKey = keyGenerator.generateKey(); // Generate secret key

        String plainText = "Hello, World!";
        System.out.println("Plain Text : " + plainText);

        byte[] cipherText = encrypt(plainText, secretKey); // Encrypt
        System.out.println("Cipher Text : " + new String(cipherText));

        String decryptedText = decrypt(cipherText, secretKey); // Decrypt
        System.out.println("Decrypted Text : " + decryptedText);
    }
}