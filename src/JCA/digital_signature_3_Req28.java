import java.security.*;
import java.util.Base64;

public class digital_signature_3_Req28 {
    public static final String MESSAGE = "This is a signed message";

    public static void main(String[] args) {
        try {
            // Generate a RSA key pair
           KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
           keyPairGenerator.initialize(2048);
           KeyPair keyPair = keyPairGenerator.generateKeyPair();

           // Sign the message
           Signature signature = Signature.getInstance("SHA256withRSA");
           signature.initSign(keyPair.getPrivate());
           signature.update(MESSAGE.getBytes());
           byte[] signedMessage = signature.sign();

           // Convert it to Base64 format
           String signedMessageBase64 = Base64.getEncoder().encodeToString(signedMessage);
           System.out.println("Signed message in Base64 format: " + signedMessageBase64);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
           e.printStackTrace();
        }
    }
}