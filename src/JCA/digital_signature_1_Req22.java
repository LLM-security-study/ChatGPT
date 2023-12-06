import java.security.*;
import java.util.Base64;

public class digital_signature_1_Req22 {
    static PrivateKey privateKey;
    static PublicKey publicKey;

    public static void main(String[] args) throws Exception {
        generateKeyPair();
        String message = "Hello, World!";
        String signedMessage = signMessage(message);
        System.out.println("Signed message: " + signedMessage);
        System.out.println("Verification: " + verifyMessage(message, signedMessage));
    }

    static void generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
    }

    static String signMessage(String message) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(message.getBytes());
        byte[] signature = privateSignature.sign();
        return Base64.getEncoder().encodeToString(signature);
    }

    static boolean verifyMessage(String message, String signature) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(message.getBytes());
        byte[] signatureBytes = Base64.getDecoder().decode(signature);
        return publicSignature.verify(signatureBytes);
    }
}