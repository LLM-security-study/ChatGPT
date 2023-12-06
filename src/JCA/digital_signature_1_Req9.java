import java.security.*;

public class digital_signature_1_Req9 {

    // Function to sign a given message
    static byte[] sign(byte[] message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }

    // Function to verify the authenticity and integrity of a signed message
    static boolean verify(byte[] message, byte[] signMessage, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message);
        return signature.verify(signMessage);
    }

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048, new SecureRandom());
        KeyPair keyPair = keyPairGen.generateKeyPair();

        byte[] message = "Hello, World!".getBytes();
        byte[] signedMessage = sign(message, keyPair.getPrivate());

        System.out.println("Verification: "
                + verify(message, signedMessage, keyPair.getPublic()));
    }
}