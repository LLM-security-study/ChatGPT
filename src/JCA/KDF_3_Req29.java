import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

public class KDF_3_Req29 {
    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    public static byte[] generateDerivedKey(char[] password, byte[] salt, int iterationCount, int keyLength)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return keyFactory.generateSecret(keySpec).getEncoded();
    }

    public static void main(String[] args) throws Exception {
        char[] password = "YourPassword".toCharArray();
        byte[] salt = new BigInteger("54786929bbe6a64ae873d214c50f0ce3", 16).toByteArray();
        int iterationCount = 10000;
        int derivedKeyLength = 256;

        byte[] derivedKey = generateDerivedKey(password, salt, iterationCount, derivedKeyLength);
        System.out.println("Derived Key: " + Arrays.toString(derivedKey));
    }
}