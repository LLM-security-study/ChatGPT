import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class sym_encryption_CBC_2_Req17 {

    private static final String ALGORITHM = "AES/CBC/PKCS5PADDING";
    private static final byte[] keyValue = new byte[] { 'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = generateIv();
            SecretKey skeySpec = new SecretKeySpec(keyValue, "AES");

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            byte[] cipherWithIv = new byte[iv.getIV().length + encrypted.length]; //cryptogram

            System.arraycopy(iv.getIV(), 0, cipherWithIv, 0, iv.getIV().length);
            System.arraycopy(encrypted, 0, cipherWithIv, iv.getIV().length, encrypted.length);

            return Base64.getEncoder().encodeToString(cipherWithIv);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String encrypted) {
        try {
            byte[] cipherWithIv = Base64.getDecoder().decode(encrypted);

            byte[] cipher = new byte[cipherWithIv.length - 16];
            byte[] iv = new byte[16];
            System.arraycopy(cipherWithIv, 0, iv, 0, 16);
            System.arraycopy(cipherWithIv, 16, cipher, 0, cipherWithIv.length - 16);

            SecretKey skeySpec = new SecretKeySpec(keyValue, "AES");

            Cipher decryptionCipher = Cipher.getInstance(ALGORITHM);
            decryptionCipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(iv));

            byte[] original = decryptionCipher.doFinal(cipher);

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv.map(byteValue -> byteValue & 0xff).toArray());
    }

    public static void main(String []args){
        String plainText = "This is a test.";
        String cipherText = encrypt(plainText);
        String decryptedCipherText = decrypt(cipherText);

        System.out.println("Original Text: " + plainText);
        System.out.println("Encrypted Text: " + cipherText);
        System.out.println("Decrypted Text: " + decryptedCipherText);
    }
}