import java.security.*;
import java.util.Base64;

public class digital_signature_3_Req23 {
    public static void main(String[] args) throws Exception {
        // Generate a new key pair for signing
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();
        PrivateKey privKey = pair.getPrivate();
        PublicKey pubKey = pair.getPublic();

        // The message to sign
        String msg = "The quick brown fox jumps over the lazy dog";

        // Sign the message
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privKey);
        sign.update(msg.getBytes());

        byte[] signature = sign.sign();

        // Print the signature
        System.out.println("Digital Signature for the message: " + msg);
        System.out.println(Base64.getEncoder().encodeToString(signature));

        // Verifying the signature
        sign.initVerify(pubKey);
        sign.update(msg.getBytes());
        boolean isVerified = sign.verify(signature);

        if(isVerified) {
            System.out.println("The signature has been verified and is valid.");
        } else {
            System.out.println("The signature is not valid.");
        }
    }
}