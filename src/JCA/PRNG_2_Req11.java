import java.security.SecureRandom;

public class PRNG_2_Req11 {
    public static void main(String[] args) {
        // get a large random number for seeding
        long seed = System.currentTimeMillis();
        
        // create an instance of SecureRandom using the given seed
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(seed);
        
        // a salt is usually of a fixed size
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        
        System.out.println("Generated salt: " + bytesToHex(salt));
    }
    
    /*
    * converting byte array into hexadecimal string
    * for easier visibility and storage. 
    */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }
}