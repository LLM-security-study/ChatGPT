import java.security.*;

public class digital_signature_1_Req14 {
    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        keyGen.initialize(1024, random);
        return keyGen.generateKeyPair();
    }

    public static byte[] signMessage(String message, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance("SHA256withDSA");
            signature.initSign(privateKey);
            signature.update(message.getBytes());
            return signature.sign();
        } catch (Exception e) {
            throw new RuntimeException("Error signing message", e);
        }
    }

    public static boolean verifyMessage(String message, byte[] signedMessage, PublicKey publicKey) {
        try {
            Signature signature = Signature.getInstance("SHA256withDSA");
            signature.initVerify(publicKey);
            signature.update(message.getBytes());
            return signature.verify(signedMessage);
        } catch (Exception e) {
            throw new RuntimeException("Error verifying message ", e);
        }
    }

    public static void main(String[] args) {
        try {
            KeyPair keyPair = generateKeyPair();
            String message = "Hello, World!";
            byte[] signedMessage = signMessage(message, keyPair.getPrivate());
            boolean isVerified = verifyMessage(message, signedMessage, keyPair.getPublic());

            System.out.println("Message Verification: " + (isVerified ? "Success" : "Failure"));
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}