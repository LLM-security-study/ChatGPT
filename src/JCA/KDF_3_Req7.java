import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

public class KDF_3_Req7 {
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String originalPassword = "password";
        String generatedSecuredPasswordHash = generateStorngPasswordHash(originalPassword);
        System.out.println(generatedSecuredPasswordHash);
        boolean matched = validatePassword("password", generatedSecuredPasswordHash);
        System.out.println(matched);
    }

    public static String generateStorngPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        // this should be replaced with a more secure method for generating salt
        return "S@ltStr1ng".getBytes(); 
    }

    public static String toHex(byte[] array) throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();
        for(byte b: array) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        return Arrays.equals(hash, testHash);
    }

    public static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}