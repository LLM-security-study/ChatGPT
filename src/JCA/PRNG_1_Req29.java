import java.security.SecureRandom;
import java.util.Random;

public class PRNG_1_Req29 {
    public static void main(String[] args) {
        // Generate seed for PRNG
        long seed = System.currentTimeMillis();
        // Initialize PRNG with seed
        Random prng = new Random(seed);
        // Use PRNG to generate IV
        byte[] iv = new byte[16];
        prng.nextBytes(iv);
        // Print IV in hexadecimal
        printBytesInHex(iv);
    }

    private static void printBytesInHex(byte[] bytes) {
        for (byte b : bytes) {
            System.out.printf("%02x", b);
        }
        System.out.println();
    }
}