import java.security.*;
import java.util.Base64;

public class digital_signature_2_Req7 {
    
    public static void main(String[] args) throws Exception {
        
        // The original data to sign
        String data = "Hello, Digital Signature!";
        
        // Generate an RSA key pair
        KeyPair keyPair = generateRSAKkeyPair();

        // Sign data
        String signature = sign(data, keyPair.getPrivate());

        // Verify signature
        boolean isCorrect = verify(data, signature, keyPair.getPublic());
        
        System.out.println("Signature correct: " + isCorrect);      
    }
    
    // Method to generate RSA key pair
    static KeyPair generateRSAKkeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048); // Key size is 2048 bits
        return keyPairGen.generateKeyPair();
    }
    
    // Method to sign data
    static String sign(String data, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(data.getBytes("UTF-8"));
        byte[] s = privateSignature.sign();
        return Base64.getEncoder().encodeToString(s);
    }

    // Method to verify signature
    static boolean verify(String data, String signature, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(data.getBytes("UTF-8"));
        byte[] signatureBytes = Base64.getDecoder().decode(signature);
        return publicSignature.verify(signatureBytes);
    }

}