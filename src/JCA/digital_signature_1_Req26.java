import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class digital_signature_1_Req26 {

    private static final String ALGORITHM = "SHA256withRSA";
    private static final String MESSAGE = "This message is to be signed and verified";

    public static void main(String[] args) throws Exception {

        // Generate KeyPair
        KeyPair keyPair = getKeyPair();

        // Sign the message
        byte[] signedMessage = signMessage(MESSAGE, keyPair.getPrivate());

        // Verify the message
        boolean isVerified = verifySignature(MESSAGE, signedMessage, keyPair.getPublic());

        System.out.println("Message is Verified : " + isVerified);
    }

    private static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    private static byte[] signMessage(String message, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance(ALGORITHM);
        privateSignature.initSign(privateKey);
        privateSignature.update(message.getBytes());

        return privateSignature.sign();
    }

    private static boolean verifySignature(String message, byte[] signed, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance(ALGORITHM);
        publicSignature.initVerify(publicKey);
        publicSignature.update(message.getBytes());

        return publicSignature.verify(signed);
    }
}