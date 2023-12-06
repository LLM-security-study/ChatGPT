import java.security.*;

public class digital_signature_1_Req11 {
    private static Signature sign;
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = generateKeyPair();
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();

        byte[] signature = signMessage("Hello, World!", privateKey);
        boolean isVerified = verifyMessage("Hello, World!", signature, publicKey);

        System.out.println("Is the signature verified? : " + isVerified);
    }

    public static byte[] signMessage(String message, PrivateKey privateKey) throws Exception {
        sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privateKey);
        sign.update(message.getBytes());

        return sign.sign();
    }

    public static boolean verifyMessage(String message, byte[] signature, PublicKey publicKey) throws Exception {
        sign = Signature.getInstance("SHA256withRSA");
        sign.initVerify(publicKey);
        sign.update(message.getBytes());

        return sign.verify(signature);
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        
        return keyPairGenerator.genKeyPair();
    }
}