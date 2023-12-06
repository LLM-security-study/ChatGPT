import java.security.*;

public class digital_signature_1_Req27 {
  
    // Create a new KeyPairGenerator object and generate a new KeyPair
    private static final KeyPair keyPair = initializeKeyPair();
  
    private static KeyPair initializeKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);
            return keyGen.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
  
    // Method for signing a message
    public static byte[] sign(String message) throws Exception {
        Signature signer = Signature.getInstance("SHA1withDSA", "SUN");
        signer.initSign(keyPair.getPrivate());
        signer.update(message.getBytes());
        return (signer.sign());
    }
  
    // Method for verifying a signed message
    public static boolean verify(String message, byte[] signature) throws Exception {
        Signature verifier = Signature.getInstance("SHA1withDSA", "SUN");
        verifier.initVerify(keyPair.getPublic());
        verifier.update(message.getBytes());
        return (verifier.verify(signature));
    }
  
    public static void main(String[] args) throws Exception {
        String message = "Hello, World!";
        byte[] signature = sign(message);
      
        boolean isAuthentic = verify(message, signature);
        System.out.println("The message is authentic: " + isAuthentic);
    }
}