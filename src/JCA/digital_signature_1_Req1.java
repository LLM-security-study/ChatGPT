import java.security.*;

public class digital_signature_1_Req1 {
    
    public static void main(String[] args) throws Exception {
        
        // Generate a pair of keys
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();
        
        // Sign a message
        String message = "This is a test message";
        byte[] signature = sign(message, priv);
        System.out.println("Signature: " + new String(signature));

        // Verify the signature
        boolean isCorrect = verify(message, signature, pub);
        System.out.println("Signature correct: " + isCorrect);
    }

    public static byte[] sign(String message, PrivateKey priv) throws Exception {
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN"); 
        dsa.initSign(priv);

        byte[] strByte = message.getBytes("UTF8");
        dsa.update(strByte);
        
        byte[] realSig = dsa.sign();
        return realSig;
    }

    public static boolean verify(String message, byte[] signature, PublicKey pub) throws Exception {
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initVerify(pub); 

        byte[] messageBytes = message.getBytes("UTF8");
        sig.update(messageBytes);
        
        return sig.verify(signature);
    }
}