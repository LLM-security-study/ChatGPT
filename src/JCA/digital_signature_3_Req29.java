import java.security.*;
import java.util.Base64;

public class digital_signature_3_Req29 {
    public static void main(String[] args) throws Exception {
        // Message to be signed
        String originalMessage = "This is a secret message";
        System.out.println("Original Message: " + originalMessage);

        // Generate public and private keys
        KeyPair keyPair = generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Sign the message
        String signature = sign(originalMessage, privateKey);
        System.out.println("Generated Signature: " + signature);

        // Verify the signature
        boolean verified = verify(originalMessage, signature, publicKey);
        System.out.println("Signature Verified: " + verified);
    }

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    private static String sign(String originalMessage, PrivateKey privateKey) 
        throws NoSuchAlgorithmException, InvalidKeyException, 
        SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(originalMessage.getBytes());
        byte[] signatureBytes = signature.sign();
        return Base64.getEncoder().encodeToString(signatureBytes);
    }

    private static boolean verify(String originalMessage, String signature,
        PublicKey publicKey) 
        throws NoSuchAlgorithmException, InvalidKeyException, 
        SignatureException {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(originalMessage.getBytes());
        byte[] signatureBytes = Base64.getDecoder().decode(signature);
        return publicSignature.verify(signatureBytes);
    }
}