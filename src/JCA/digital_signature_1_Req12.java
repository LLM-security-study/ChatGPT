import java.security.*;

public class digital_signature_1_Req12 {
    public static void main(String[] args) throws Exception {
        // Simply String example for signing
        String message = "Hello, World!";

        // Generate key pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair keyPair = kpg.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Sign
        byte[] signedMessage = sign(message.getBytes(), privateKey);

        // Verify
        boolean isValid = verify(message.getBytes(), signedMessage, publicKey);
        System.out.println("Is valid: " + isValid);
    }

    public static byte[] sign(byte[] message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }

    public static boolean verify(byte[] message, byte[] signedMessage, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message);
        return signature.verify(signedMessage);
    }
}