import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;

public class digital_signature_3_Req5 {
    public static void main(String[] args) throws Exception {
        String message = "This is the message to be signed";

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(keyPair.getPrivate());
        signature.update(message.getBytes());

        byte[] digitalSignature = signature.sign();
        System.out.println("Digital Signature: " + new String(digitalSignature));
    }
}