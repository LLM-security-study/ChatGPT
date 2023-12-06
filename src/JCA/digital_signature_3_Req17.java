import java.security.*;
import java.util.Base64;

public class digital_signature_3_Req17 {
    private static String message = "Hello, World!";

    public static void main(String[] args) {
        try {
            // Generate a key pair
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);
            KeyPair pair = keyGen.generateKeyPair();
            PrivateKey privateKey = pair.getPrivate();

            // Sign the message
            Signature signature = Signature.getInstance("SHA1withDSA", "SUN"); 
            signature.initSign(privateKey);
            signature.update(message.getBytes("UTF8"));
            byte[] signedMessage = signature.sign();

            // Convert the signed message into Base64 for readability
            String base64SignedMessage = Base64.getEncoder().encodeToString(signedMessage);

            // Print the signed message
            System.out.println("Signed message: " + base64SignedMessage);
        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }
    }
}