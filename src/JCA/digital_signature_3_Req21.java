import java.nio.charset.StandardCharsets;
import java.security.*;

public class digital_signature_3_Req21 {
    public static void main(String args[]) throws Exception {
        String originalMessage = "This is a text message.";

        // Generate key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        // Generate signature
        Signature signatureGenerator = Signature.getInstance("SHA256withRSA");
        signatureGenerator.initSign(privateKey);
        signatureGenerator.update(originalMessage.getBytes(StandardCharsets.UTF_8));
        byte[] signature = signatureGenerator.sign();

        // Verify signature
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(originalMessage.getBytes(StandardCharsets.UTF_8));
        boolean isCorrect = publicSignature.verify(signature);

        System.out.println("Original Message: " + originalMessage);
        System.out.println("Signature: " + new String(signature, StandardCharsets.UTF_8));
        System.out.println("Does signature match: " + isCorrect);

    }
}