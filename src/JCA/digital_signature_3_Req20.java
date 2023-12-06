import java.security.*;
import java.util.Base64;

public class digital_signature_3_Req20 {
    private PrivateKey privateKey;

    public static void main(String[] args) throws Exception {
        // Your message
        String message = "Hello, World!";

        digital_signature_3_Req20 main = new digital_signature_3_Req20();
        main.genKeyPair();

        String signature = main.sign(message, main.privateKey);
        System.out.println("Signature: " + signature);
    }

    public void genKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        privateKey = pair.getPrivate();
    }

    public String sign(String plainText, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes());

        byte[] signature = privateSignature.sign();

        return Base64.getEncoder().encodeToString(signature);
    }
}