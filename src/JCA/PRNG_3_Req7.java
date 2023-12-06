import java.security.SecureRandom;

public class PRNG_3_Req7 {
    public static void main(String[] args) {
        
        // Creating a SecureRandom object
        SecureRandom secureRandom = new SecureRandom();

        // Creating a seed
        byte[] seed = new byte[12];
        secureRandom.nextBytes(seed);
        
        // Initialize with the generated seed
        secureRandom.setSeed(seed);
        
        // Generating a random integer
        int randomNumber = secureRandom.nextInt();

        System.out.println("Generated seed: " + bytesToHex(seed));
        System.out.println("Generated Random Number: " + randomNumber);
    }

    // Method to convert byte array into a hexadecimal string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}