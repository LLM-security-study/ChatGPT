import java.security.*;
import java.util.Arrays;

public class digital_signature_1_Req10 {

    public static byte[] sign(String plainText, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes("UTF-8"));
        byte[] signature = privateSignature.sign();
        return signature;
    }

    public static boolean verify(String plainText, byte[] signature, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes("UTF-8"));
        return publicSignature.verify(signature);
    }

    public static void main(String[] args) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();

        String plainText = "Hello, World!";

        byte[] signature = sign(plainText, kp.getPrivate());

        boolean isCorrect = verify(plainText, signature, kp.getPublic());

        System.out.println("Signature correct: " + isCorrect);
    }
}