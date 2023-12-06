import java.security.*;
import java.util.Arrays;

public class digital_signature_1_Req7 {
    public static void main(String[] args) throws Exception {
        // Create a Key Pair Generator
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        keyGen.initialize(1024, random);

        // Generate Key Pair
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        // Sign the message
        byte[] realSig = sign(priv, "message to be signed");
        
        // Verify the message
        boolean isVerified = verify(pub, "message to be signed", realSig);
        System.out.println("Signature Verified: " + isVerified);
    }
    
    public static byte[] sign(PrivateKey key, String message) throws Exception {
        Signature dsa = Signature.getInstance("SHA1withDSA"); 
        dsa.initSign(key);
        dsa.update(message.getBytes());
        return dsa.sign();
    }
    
    public static boolean verify(PublicKey key, String message, byte[] signature) throws Exception {
        Signature sig = Signature.getInstance("SHA1withDSA");
        sig.initVerify(key);
        sig.update(message.getBytes());
        return sig.verify(signature);
    }
}