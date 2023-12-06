import java.security.*;

public class digital_signature_3_Req3 {
    public static void main(String[] args) throws Exception {
        // Get instance and generate keys
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();
        
        //Message String
        String message = "Hello, this is the message to be signed.";

        /* Signing the message */
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN"); 
        dsa.initSign(priv);
        dsa.update(message.getBytes("UTF8"));
        byte[] sig = dsa.sign();
        
        /* Verify the signature with the public key */
        Signature sig2 = Signature.getInstance("SHA1withDSA", "SUN");
        sig2.initVerify(pub);
        sig2.update(message.getBytes("UTF8"));
        boolean verifies = sig2.verify(sig);
        System.out.println("signature verifies: " + verifies);
    }
}