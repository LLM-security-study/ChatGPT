import java.security.*;
import java.util.Base64;

public class digital_signature_3_Req14 {
    public static void main(String[] args) throws Exception {
        String originalData = "The data to be signed.";

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();

        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(originalData.getBytes("UTF8"));

        byte[] signature = privateSignature.sign();

        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
    }
}