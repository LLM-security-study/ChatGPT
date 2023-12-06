import java.security.*;

public class digital_signature_1_Req6 {
    public static void main(String[] args) throws Exception {

        // Generate a DSA key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        SecureRandom random = SecureRandom.getInstanceStrong();
        keyGen.initialize(1024, random);

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey privKey = pair.getPrivate();
        PublicKey pubKey = pair.getPublic();

        // Prepare the input message
        byte[] message = "Hello, World!".getBytes("UTF8");

        // Sign the Message
        byte[] digitalSignature = signMessage(privKey, message);

        // Verify the signature
        boolean isCorrect = verifySignature(pubKey, message, digitalSignature);
        System.out.println("Signature correct: " + isCorrect);
    }

    // Method for signing a message
    public static byte[] signMessage(PrivateKey privKey, byte[] message) throws Exception {

        Signature dsa = Signature.getInstance("SHA256withDSA");
        dsa.initSign(privKey);

        dsa.update(message);
        return dsa.sign();
    }

    // Method for verifying a signature
    public static boolean verifySignature(PublicKey pubKey, byte[] message, byte[] digitalSignature) throws Exception {

        Signature sig = Signature.getInstance("SHA256withDSA");
        sig.initVerify(pubKey);

        sig.update(message);
        return sig.verify(digitalSignature);
    }
}