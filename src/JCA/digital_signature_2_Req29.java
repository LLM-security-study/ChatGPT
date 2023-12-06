import java.security.*;
import java.util.Base64;

public class digital_signature_2_Req29 {
    
    public static void main(String[] args) throws Exception {
        // Generate key pair
        KeyPair keyPair = generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // The original data
        String originalData = "Hello, World!";
        System.out.println("Original Data: " + originalData);

        // Generate digital signature
        byte[] digitalSignature = signDigitalSignature(originalData, privateKey);

        // Verify digital signature
        boolean isVerify = verifySignature(originalData, digitalSignature, publicKey);
        System.out.println("Digital Signature Verified: " + isVerify);
    }

    private static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        return keyPairGen.generateKeyPair();
    }

    private static byte[] signDigitalSignature(String data, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(data.getBytes());
        byte[] signature = privateSignature.sign();
        return signature;
    }

    private static boolean verifySignature(String data, byte[] signature, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(data.getBytes());
        byte[] signatureBytes = signature;
        return publicSignature.verify(signatureBytes);
    }
}