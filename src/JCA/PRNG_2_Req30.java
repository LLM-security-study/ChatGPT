import java.security.SecureRandom;

public class PRNG_2_Req30 {
    public static void main(String[] args) {
        // Create a SecureRandom instance.
        SecureRandom sr = new SecureRandom();

        // Generate a seed for PRNG.
        byte[] seed = sr.generateSeed(20); // 20 is just an arbitrary number. You can use any number you want.
        sr.setSeed(seed);

        // Now let's generate a salt for password hashing.
        byte[] salt = new byte[16]; // Creating a byte array for salt. 16 is a popular choice for salt size.

        // Use the SecureRandom instance to fill our salt byte array with random numbers.
        sr.nextBytes(salt);

        // Print out the salt
        System.out.println(bytesToHex(salt));
    }


    // Method to convert byte array to hexadecimal
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}