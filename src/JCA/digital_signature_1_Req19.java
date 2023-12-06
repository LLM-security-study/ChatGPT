import java.security.*;
import java.util.Base64;

public class digital_signature_1_Req19 {
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    // Load the Public Key and Private Key Once
    static {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String sign(String plainText) {
        try {
            Signature privateSignature = Signature.getInstance("SHA256withRSA");
            privateSignature.initSign(privateKey);
            privateSignature.update(plainText.getBytes());
            byte[] signature = privateSignature.sign();

            return Base64.getEncoder().encodeToString(signature);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static boolean verify(String plainText, String signature) {
        try {
            Signature publicSignature = Signature.getInstance("SHA256withRSA");
            publicSignature.initVerify(publicKey);
            publicSignature.update(plainText.getBytes());

            return publicSignature.verify(Base64.getDecoder().decode(signature));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        String message = "This is a test message";
        System.out.println("Original Message: " + message);

        String signature = sign(message);
        System.out.println("Generated Signature: " + signature);

        boolean isCorrect = verify(message, signature);
        System.out.println("Signature verified: " + isCorrect);
    }
}