import java.security.*;

public class digital_signature_1_Req13 {
    // Function to sign a message
    public static byte[] sign(String data, PrivateKey key) throws Exception {
        Signature signer = Signature.getInstance("SHA256withDSA");
        
        signer.initSign(key);
        
        signer.update(data.getBytes());
        
        return signer.sign();
    }

    // Function to verify a signed message
    public static boolean verify(String data, byte[] signature, PublicKey key) throws Exception {
        Signature verifier = Signature.getInstance("SHA256withDSA");
        
        verifier.initVerify(key);
        
        verifier.update(data.getBytes());
        
        return verifier.verify(signature);
    }

    public static void main(String args[]) throws Exception {
        // Generation of a public and a private key
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");

        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        String message = "Hello, world!";
        
        // Sign the message
        byte[] signature = sign(message, priv);

        // Verify the signature
        boolean verification = verify(message, signature, pub);
        System.out.println("Verification: " + verification);
    }
}