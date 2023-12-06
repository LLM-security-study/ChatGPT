import java.security.*;

public class digital_signature_2_Req20 {

    public static void main(String[] args) throws Exception {
        // Data to sign
        byte[] data = "DigitalSignatureExample".getBytes("UTF8");

        // Generate key pair
        KeyPair keyPair = generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Generate the signature
        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initSign(privateKey);
        signature.update(data);
        byte[] digitalSignature = signature.sign();

        // Verify the signature
        boolean isVerified = verifySignature(publicKey, digitalSignature, data);
        System.out.println("The digital signature was verified: " + isVerified);
    }

    public static KeyPair generateKeyPair() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        return keyPairGenerator.generateKeyPair();
    }

    public static boolean verifySignature(PublicKey publicKey, byte[] digitalSignature, byte[] data) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256WithRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(data);
        return publicSignature.verify(digitalSignature);
    }
}