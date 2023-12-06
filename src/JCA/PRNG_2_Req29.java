import java.security.SecureRandom;

public class PRNG_2_Req29 {
    public static final int SALT_LENGTH = 16; // You can choose the length as per your requirement

    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt); // This will generate salt

        // Print salt by converting it to Hexadecimal form
        StringBuilder saltInHex = new StringBuilder();
        for(byte b: salt){
            saltInHex.append(String.format("%02X", b));
        }
        System.out.println("Generated Salt in hex form: " + saltInHex.toString());
    }
}