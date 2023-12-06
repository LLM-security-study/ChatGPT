import javax.crypto.Cipher;
import java.security.*;

public class asym_encryption_2_Req22 {

    public static void main(String[] args) throws Exception {
        // Generating a new key pair
        KeyPair keyPair = generateKeyPair();

        // The original data
        String originalData = "Hello, World!";

        // Encrypt data
        byte[] encryptedData = encrypt(keyPair.getPublic(), originalData);

        // Decrypt data
        String decryptedData = decrypt(keyPair.getPrivate(), encryptedData);

        System.out.println("Original data: " + originalData);
        System.out.println("Decrypted data: " + decryptedData);
    }

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        // Create a key pair generator and initialise with key size
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048, new SecureRandom());
        return keyGen.generateKeyPair();
    }

    private static byte[] encrypt(PublicKey publicKey, String data) 
                                throws NoSuchPaddingException, NoSuchAlgorithmException, 
                                InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data.getBytes());
    }

    private static String decrypt(PrivateKey privateKey, byte[] encryptedData) 
                                throws NoSuchPaddingException, NoSuchAlgorithmException, 
                                InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(encryptedData));
    }
}