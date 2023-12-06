import java.security.*;

public class digital_signature_2_Req17 {
    public static void main(String[] args) throws Exception {
        // Let's start by generating a private key
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();
        PrivateKey privKey = pair.getPrivate();
        PublicKey pubKey = pair.getPublic();

        // Data to sign.
        byte[] data = "Test data".getBytes("UTF8");

        // Compute signature.
        Signature instance = Signature.getInstance("SHA256withRSA");
        instance.initSign(privKey);
        instance.update(data);
        byte[] signature = instance.sign();

        // Now let's verify the signature
        instance.initVerify(pubKey);
        instance.update(data);
        boolean verifies = instance.verify(signature);
        System.out.println("Signature verifies: " + verifies);
    }
}