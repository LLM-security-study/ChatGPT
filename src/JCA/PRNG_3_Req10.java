public class PRNG_3_Req10 {
    public static void main(String[] args) {
        // Create a secure random number generator using the "SHA1PRNG" algorithm
        SecureRandom secureRandom = new SecureRandom();

        // Get the seed
        byte[] seed = secureRandom.generateSeed(20);

        // Now create a secure random number generator with the obtained seed
        SecureRandom seededRandom = new SecureRandom(seed);

        // Generate a secure random number intended for cryptographic applications
        int randomInt = seededRandom.nextInt();
        System.out.println("The generated random integer is: " + randomInt);
    }
}