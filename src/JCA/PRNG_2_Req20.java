import java.security.SecureRandom;

public class PRNG_2_Req20 {
    public static void main(String[] args) {
        // Create a SecureRandom instance
        SecureRandom prng = new SecureRandom();

        // Generate a seed for the PRNG
        byte[] seed = new byte[64];
        prng.nextBytes(seed);

        // Initialize the PRNG with the generated seed
        prng.setSeed(seed);

        // Generate a salt for password hashing
        byte[] salt = new byte[16];
        prng.nextBytes(salt);

        System.out.println("Generated salt: " + bytesToHex(salt));
    }

    // Function to convert byte array to hexadecimal
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}