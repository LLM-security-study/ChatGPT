import java.security.*;

public class digital_signature_2_Req5 {
    public static void main(String[] args) throws Exception {
        // Generate key pair
        KeyPair keyPair = generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Original message
        String originalMessage = "This is the message to be signed and verified.";

        // Perform digital signature
        byte[] digitalSignature = signDigitalSignature(originalMessage, privateKey);

        // Verification of the digital signature
        boolean isCorrect = verifyDigitalSignature(originalMessage, digitalSignature, publicKey);
        System.out.println("Digital Signature Verification : " + (isCorrect ? "Verified" : "Not Verified"));
    }

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        return keyGen.genKeyPair();
    }

    private static byte[] signDigitalSignature(String data, PrivateKey privateKey) throws Exception {
        Signature rsa = Signature.getInstance("SHA256withRSA");
        rsa.initSign(privateKey);
        rsa.update(data.getBytes());
        return rsa.sign();
    }

    private static boolean verifyDigitalSignature(String data, byte[] digitalSignature, PublicKey publicKey) throws Exception {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initVerify(publicKey);
        sign.update(data.getBytes());
        return sign.verify(digitalSignature);
    }
}