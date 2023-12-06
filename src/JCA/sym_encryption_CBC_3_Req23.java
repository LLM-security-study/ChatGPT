import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.security.InvalidKeyException;
import java.security.InvalidAlgorithmParameterException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public class sym_encryption_CBC_3_Req23 {
    private static final String ALGORITHM = "AES/CBC/PKCS5PADDING";

    public static String decrypt(byte[] cipherText, Key key, byte[] iv) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            AlgorithmParameterSpec algorithmParams = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, key, algorithmParams);
            byte[] decryptedText = cipher.doFinal(cipherText);
            return new String(decryptedText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        byte[] keyBytes = new byte[16]; // Initialize with the key bytes
        byte[] iv = new byte[16]; // Initialize with the IV bytes
        byte[] cipherText = new byte[16]; // Initialize with the cypher text bytes

        Key key = new SecretKeySpec(keyBytes, "AES");

        System.out.println("Decrypted Text: "+ decrypt(cipherText, key, iv));
    }
}