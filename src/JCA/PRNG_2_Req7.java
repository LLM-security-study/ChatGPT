import java.security.SecureRandom;

public class PRNG_2_Req7 {

    public static void main(String[] args) {
        // Create a SecureRandom instance
        SecureRandom random = new SecureRandom();

        // Generate a seed for PRNG
        byte[] seed = random.generateSeed(20);
        System.out.println("Seed: " + bytesToHex(seed));

        // Initialize PRNG with the seed
        random.setSeed(seed);

        // Generate a salt for password hashing
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        System.out.println("Salt: " + bytesToHex(salt));
    }

    /**
     * Converts a byte array to a hexadecimal String.
     *
     * @param bytes The byte array
     * @return The hexadecimal String
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xff & bytes[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}