import java.security.*;

public class digital_signature_1_Req23 {
    private static Signature signature;

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        byte[] originalMsg = "Your message goes here".getBytes("UTF8");
        byte[] signedMsg = signMessage(originalMsg, priv);

        verifySignature(originalMsg, signedMsg, pub);
    }

    public static byte[] signMessage(byte[] message, PrivateKey privateKey) throws Exception {
        signature = Signature.getInstance("SHA1withDSA");
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }

    public static void verifySignature(byte[] originalMessage, byte[] signedMessage, PublicKey publicKey) throws Exception {
        signature.initVerify(publicKey);
        signature.update(originalMessage);

        if (signature.verify(signedMessage)) {
            System.out.println("Signature is valid");
        } else {
            System.out.println("Signature is NOT valid");
        }
    }
}