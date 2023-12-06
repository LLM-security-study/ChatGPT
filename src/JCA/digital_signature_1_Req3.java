import java.security.*;

public class digital_signature_1_Req3 {
    
    public static void main(String[] args) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        String message = "Hello, World!";
        
        // Generate a key pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        kpg.initialize(1024, random);
        KeyPair pair = kpg.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        
        // Sign the message
        byte[] signatureBytes = sign(message, privateKey);
        
        // Verify the signature
        boolean isVerified = verify(message, signatureBytes, publicKey);
        System.out.println("Signature verification: " + (isVerified ? "valid" : "invalid"));
    }

    public static byte[] sign(String message, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("DSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        return signature.sign();
    }

    public static boolean verify(String message, byte[] signatureBytes, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("DSA");
        signature.initVerify(publicKey);
        signature.update(message.getBytes());
        return signature.verify(signatureBytes);
    }
}