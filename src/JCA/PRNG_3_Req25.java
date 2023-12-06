import java.security.SecureRandom;

public class PRNG_3_Req25 {
    public static void main(String[] args) {
        // Initialize SecureRandom
        // This is a lengthy operation, to be done only upon
        // initialization of the application
        SecureRandom secureRandom = new SecureRandom();

        // Generate a seed
        byte[] seed = new byte[20];
        secureRandom.nextBytes(seed);

        // Initialize SecureRandom with the generated seed
        SecureRandom random = new SecureRandom(seed);

        // Generate a random number intended for cryptographic use
        int randomNumber = random.nextInt();

        System.out.println("The generated random number is " + randomNumber);
    }
}