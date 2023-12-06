import java.nio.charset.StandardCharsets;
import java.security.*;

public class digital_signature_3_Req6 {
    public static void main(String[] args) throws Exception {
        // Our secret message
        String secretMessage = "This is a private message.";

        // Generate new key pair for signing and verification
        KeyPair keyPair = generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Sign the secret message with the private key
        byte[] signature = sign(privateKey, secretMessage);

        // Assume here that the signature and the publicKey are sent over to a recipient.
        // The recipient can then use the public key to verify the signature

        // Verify the signature
        boolean iCanTrustThisMessage = verify(publicKey, secretMessage, signature);

        System.out.println("Can I trust this message? : " + iCanTrustThisMessage);
    }

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        return keyPairGenerator.generateKeyPair();
    }

    private static byte[] sign(PrivateKey privateKey, String message) throws Exception {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privateKey);
        sign.update(message.getBytes(StandardCharsets.UTF_8));
        return sign.sign();
    }

    private static boolean verify(PublicKey publicKey, String message, byte[] signature) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(message.getBytes(StandardCharsets.UTF_8));
        return publicSignature.verify(signature);
    }
}