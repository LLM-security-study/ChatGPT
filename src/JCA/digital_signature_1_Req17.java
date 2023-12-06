import java.security.*;
import java.nio.charset.StandardCharsets;

public class digital_signature_1_Req17 {

    static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    static byte[] sign(String plainText, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes(StandardCharsets.UTF_8));

        byte[] signature = privateSignature.sign();

        return signature;
    }

    static boolean verify(String plainText, byte[] signature, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes(StandardCharsets.UTF_8));

        return publicSignature.verify(signature);
    }

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = generateKeyPair();

        String message = "The quick brown fox jumps over the lazy dog.";

        byte[] signature = sign(message, keyPair.getPrivate());

        System.out.println("Signature: " + new String(signature, StandardCharsets.UTF_8));  //print out the signature

        System.out.println("Verification: " + verify(message, signature, keyPair.getPublic()));  //should return true   
    }
}