import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class sym_encryption_CBC_2_Req30 {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String AES = "AES";
    private static final byte[] keyValue = new byte[]{'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};

    public static void main(String[] args) throws Exception {

        String valueToEnc = "TheDataToEncrypt";
        String encryptedValue = encrypt(valueToEnc);
        System.out.println("Encrypted value: " + encryptedValue);

    }

    public static String encrypt(String valueToEnc) throws Exception {

        SecureRandom secureRandom = new SecureRandom();
        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);

        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyValue, AES);

        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encValue = c.doFinal(valueToEnc.getBytes());
        byte[] finalCiphertext = new byte[iv.length + encValue.length];

        System.arraycopy(iv, 0, finalCiphertext, 0, iv.length);
        System.arraycopy(encValue, 0, finalCiphertext, iv.length, encValue.length);

        return Base64.getEncoder().encodeToString(finalCiphertext);
    }

}