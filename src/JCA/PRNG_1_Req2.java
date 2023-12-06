import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req2 {
    public static void main(String[] args) {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            byte[] seed = new byte[16];
            secureRandom.nextBytes(seed); // generate a seed

            secureRandom = new SecureRandom(seed); // reinitialize with seed
            byte[] iv = new byte[16];
            secureRandom.nextBytes(iv); // generate an IV

            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // Now you can use the ivSpec for encryption
            System.out.println("IV: " + byteArrayToHex(iv));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // Convert byte array to hex string for readability
    public static String byteArrayToHex(byte[] a) {
       StringBuilder sb = new StringBuilder(a.length * 2);
       for(byte b: a)
          sb.append(String.format("%02x", b));
       return sb.toString();
    }
}