import java.security.SecureRandom;

public class PRNG_2_Req18 {
   
    public static void main(String[] args) {
        // Create a SecureRandom instance
        SecureRandom secureRandom = new SecureRandom();
        
        // Generate a seed
        byte[] seed = new byte[20];
        secureRandom.nextBytes(seed);
        
        // Use the seed to re-initialize the SecureRandom instance.
        secureRandom.setSeed(seed);

        // Generate salt
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);

        // Prints the generated salt
        System.out.println("Generated Salt: " + bytesToHex(salt));
    }

    // Convert byte array to hex string for better readability
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }
}