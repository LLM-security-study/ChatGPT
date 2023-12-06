import java.security.*;
import java.util.Base64;

public class digital_signature_3_Req13 {

    public static void main(String[] args) throws Exception {

        // The message to sign
        String message = "This is a test message";

        // Generate a key pair
        KeyPair keyPair = generateKeyPair();

        // Sign the message
        String signature = signMessage(message, keyPair.getPrivate());

        System.out.println("Message: " + message);
        System.out.println("Signature: " + signature);
        System.out.println("The signature is " + (verifySignature(message, signature, keyPair.getPublic()) ? "valid" : "not valid"));
    }

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();

        return pair;
    }

    private static String signMessage(String message, PrivateKey privateKey) throws Exception {

        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(message.getBytes(StandardCharsets.UTF_8));

        byte[] signature = privateSignature.sign();

        return Base64.getEncoder().encodeToString(signature);
    }

    private static boolean verifySignature(String message, String signature, PublicKey publicKey) throws Exception {

        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(message.getBytes(StandardCharsets.UTF_8));

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        return publicSignature.verify(signatureBytes);
    }
}