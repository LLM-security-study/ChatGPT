import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class sym_encryption_2_Req7 {
    public static void main(String[] args) {
        try {
            // Create a KeyGenerator
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            keygen.init(128);
            // Generate the secret key
            SecretKey skey = keygen.generateKey();
            // Get the bytes of the secret key
            byte[] raw = skey.getEncoded();

            // Set the cipher to AES
            Cipher cipher = Cipher.getInstance("AES");
            // Initialize to encrypt mode
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            String message = "Hello, World!";
            System.out.println("Original message: " + message);

            // Encrypt the message
            byte[] encrypted = cipher.doFinal(message.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : encrypted) {
                sb.append((char)b);
            }

            // Print the encrypted message
            System.out.println("Encrypted message: " + sb.toString());

            // Initialize to decrypt mode
            cipher.init(Cipher.DECRYPT_MODE, skey);
            // Decrypt the message
            byte[] decrypted = cipher.doFinal(encrypted);
            String decryptedMessage = new String(decrypted);

            // Print the decrypted message
            System.out.println("Decrypted message: " + decryptedMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}