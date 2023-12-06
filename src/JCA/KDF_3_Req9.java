import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

class KDF_3_Req9 {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
        try {
            String password = "mypassword";
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];
            sr.nextBytes(salt);

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt, 10000, 128);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyFactory.generateSecret(pbeKeySpec).getEncoded(), "AES");

            // Print key
            for (byte b : secretKeySpec.getEncoded()) {
                System.out.format("%02x", b);
            }
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}