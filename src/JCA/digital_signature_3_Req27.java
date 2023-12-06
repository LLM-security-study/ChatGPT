import java.security.*;
import java.util.Base64;

public class digital_signature_3_Req27 {
    public static void main(String[] args) throws Exception {
        // Generating RSA key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Input message
        String msg = "Hello, this is a message to sign";

        // Getting the signature instance
        Signature signature = Signature.getInstance("SHA256withRSA");
        
        // Initializing the signature
        signature.initSign(keyPair.getPrivate());

        // Adding data to the signature
        signature.update(msg.getBytes());

        // Compute the signature
        byte[] signatureBytes = signature.sign();

        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signatureBytes));

        // Now let's verify the signature

        // Initializing the signature
        signature.initVerify(keyPair.getPublic());
        
        // Adding data to the signature
        signature.update(msg.getBytes());

        // Verify the signature
        boolean isValid = signature.verify(signatureBytes);

        System.out.println("Signature valid: " + isValid);
    }
}