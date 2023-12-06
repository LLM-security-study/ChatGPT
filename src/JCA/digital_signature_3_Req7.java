import java.security.*;
import java.util.Base64;

public class digital_signature_3_Req7 {
    public static void main(String[] args) throws Exception {
        // Prepare the message
        String message = "Hello, world!";

        // Generate a key pair
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();

        // Sign the message
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(keyPair.getPrivate());
        signature.update(message.getBytes());
        byte[] digitalSignature = signature.sign();

        // Print the signature
        System.out.println("Digital Signature: " + Base64.getEncoder().encodeToString(digitalSignature));
    }
}