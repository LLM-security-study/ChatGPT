import java.security.*;

public class digital_signature_1_Req29 {
    public static void main(String[] args) throws Exception {

        // create a key pair generator
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");

        // Initialize the key pair generator
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);

        // Generate the Pair of Keys
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        byte[] message = "Hello, world!".getBytes();

        byte[] signedMessage = signMessage(priv, message);
        System.out.println(new String(signedMessage));

        System.out.println(verifySignedMessage(pub, message, signedMessage));
    }

    public static byte[] signMessage(PrivateKey priv, byte[] message) throws Exception {
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN"); 

        dsa.initSign(priv);

        dsa.update(message);

        return dsa.sign();
    }

    public static boolean verifySignedMessage(PublicKey pub, byte[] message, byte[] signedMessage) throws Exception {
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initVerify(pub);

        sig.update(message);

        return sig.verify(signedMessage);
    }
}