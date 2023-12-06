import java.security.*;
import java.util.Arrays;

public class digital_signature_1_Req24 {
    public static void main(String[] args) throws Exception {
        // Generate a KeyPair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        // Sign a message
        byte[] message = "This is a test message".getBytes();
        byte[] signatureBytes = sign(priv, message);

        // Verify the signature
        boolean verificationResult = verify(pub, message, signatureBytes);
        System.out.println("Verification result: " + verificationResult);
    }

    private static byte[] sign(PrivateKey priv, byte[] message) throws Exception{
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
        dsa.initSign(priv);

        dsa.update(message);
        return dsa.sign();
    }

    private static boolean verify(PublicKey pub, byte[] message, byte[] signature) throws Exception {
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initVerify(pub);

        sig.update(message);
        return sig.verify(signature);
    }
}