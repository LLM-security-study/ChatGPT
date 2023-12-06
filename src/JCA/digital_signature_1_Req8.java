import java.security.*;

public class digital_signature_1_Req8 {
    private static PrivateKey privateKey;
    private static PublicKey publicKey;

    public static void main(String[] args) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);

            KeyPair pair = keyGen.generateKeyPair();
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();

            byte[] signedMessage = signMessage("This is a message to sign");

            boolean isVerified = verifySignature("This is a message to sign", signedMessage);
            System.out.println("Signature verified: " + isVerified);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] signMessage(String message) throws Exception {
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
        dsa.initSign(privateKey);

        byte[] strByte = message.getBytes();
        dsa.update(strByte);

        byte[] realSig = dsa.sign();
        return realSig;
    }

    public static boolean verifySignature(String message, byte[] signedMessage) throws Exception {
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initVerify(publicKey);

        byte[] data = message.getBytes();
        sig.update(data);

        boolean verifies = sig.verify(signedMessage);

        return verifies;
    }
}
