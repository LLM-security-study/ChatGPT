import java.security.*;
import java.util.Base64;

public class digital_signature_3_Req12 {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
    // Create a Key Pair Generator
    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);

    // Generate Key Pairs
    KeyPair keyPair = kpg.generateKeyPair();
    PrivateKey privateKey = keyPair.getPrivate();
    PublicKey publicKey = keyPair.getPublic();

    // Get instance and Initialize the signature
    Signature sign = Signature.getInstance("SHA256withRSA");
    sign.initSign(privateKey);

    byte[] bytes = "Hello, World!".getBytes();

    // Add data to the Signature and then sign it
    sign.update(bytes);
    byte[] signature = sign.sign();

    // Let's check the signature
    boolean isReal = verify(bytes, signature, publicKey);
    System.out.println("Signature is authentic: " + isReal);

    }

    public static boolean verify(byte[] data, byte[] signature, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initVerify(publicKey);
        sign.update(data);
        return sign.verify(signature);
    }
}