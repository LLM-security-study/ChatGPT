import java.security.*;
import java.util.Base64;

public class digital_signature_3_Req25 {

    public static void main(String[] args) throws Exception {

        // Generate the key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);
        KeyPair pair = keyGen.generateKeyPair();

        // Sign the message
        Signature dsa = Signature.getInstance("SHA256withDSA", "SUN");
        dsa.initSign(pair.getPrivate());
        String message = "This is a testing message";
        dsa.update(message.getBytes());
        byte[] signature = dsa.sign();

        // Convert the signature to a string
        String signatureString = Base64.getEncoder().encodeToString(signature);
        System.out.println("Signed message: " + signatureString);
    }
}