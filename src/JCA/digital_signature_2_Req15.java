import java.security.*;
import java.util.Base64;

public class digital_signature_2_Req15 {

    public static void main(String[] args) {
        try {
            // Generate a KeyPair
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);
            KeyPair keyPair = keyPairGen.generateKeyPair();

            // Sign data
            String data = "I am data to be signed.";
            byte[] digitalSignature = signData(data, keyPair.getPrivate());

            // Verify signature
            boolean isVerified = verifySignature(data, digitalSignature, keyPair.getPublic());
            System.out.println("Signature Verified: " + isVerified);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] signData(String data, PrivateKey privateKey) throws Exception {
        Signature rsa = Signature.getInstance("SHA256withRSA");
        rsa.initSign(privateKey);
        rsa.update(data.getBytes());
        return rsa.sign();
    }


    public static boolean verifySignature(String data, byte[] signature, PublicKey publicKey) throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(publicKey);
        sig.update(data.getBytes());
        return sig.verify(signature);
    }
}