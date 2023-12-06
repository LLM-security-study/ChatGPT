import java.security.*;

public class digital_signature_2_Req3 {
    public static void main(String[] args) throws Exception {
        // Generate a key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN"); 
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN"); 
        keyGen.initialize(1024, random); 
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();
         
        byte[] dataToSign = "data to be signed".getBytes("UTF8");
         
        // Sign the data
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN"); 
        dsa.initSign(priv);
        dsa.update(dataToSign);
        byte[] signature = dsa.sign();
         
        // Verify the signature
        dsa.initVerify(pub);
        dsa.update(dataToSign);
        boolean verifies = dsa.verify(signature);
        System.out.println("Signature verifies: " + verifies);
    }
}