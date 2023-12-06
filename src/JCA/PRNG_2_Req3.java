import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Random;

public class PRNG_2_Req3 {
    private static final char[] ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

    public static void main(String[] args) {
        long seed = getSeedForPRNG();
        System.out.println("Generated Seed: " + seed);
        String salt = generateSalt(16, new SecureRandom());
        System.out.println("Generated Salt: " + salt);
    }

    private static long getSeedForPRNG() {
        return new SecureRandom().nextLong();
    }

    private static String generateSalt(int size, Random random) {
        char[] chars = new char[size];
        for (int charPosition = 0; charPosition < size; charPosition++) {
            chars[charPosition] = ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)];
        }
        return new String(chars);
    }
}