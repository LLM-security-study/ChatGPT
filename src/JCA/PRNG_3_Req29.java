import java.security.SecureRandom;

public class PRNG_3_Req29 { 
    public static void main(String[] args) {
        // Create a secure random number generator
        SecureRandom secureRandom = new SecureRandom();
        
        // Generate a seed for the random number generator
        byte[] seed = new byte[8]; // You can change size as per your needs
        secureRandom.nextBytes(seed);
        
        // Use the seed to initialize the random number generator
        secureRandom.setSeed(seed);

        // Generate a random integer
        int randomInt = secureRandom.nextInt();
        
        System.out.println("Seed: " + bytesToHex(seed));
        System.out.println("Random Integer: " + randomInt);
    }

    // Function to convert byte array to hexadecimal
    static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}