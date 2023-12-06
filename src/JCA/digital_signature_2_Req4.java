import java.security.*;
import java.util.Base64;

public class digital_signature_2_Req4 {
    public static void main(String args[]) throws Exception {

        // Initialize the key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair keyPair = keyGen.genKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // generating a signature
        String data = "This is data to be signed";
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        byte[] digitalSignature = signature.sign();
        System.out.println("Digital Signature: " + Base64.getEncoder().encodeToString(digitalSignature));

        // Verifying a signature
        data = "This is data to be verified";
        signature.initVerify(publicKey);
        signature.update(data.getBytes());
        if (signature.verify(digitalSignature))
            System.out.println("Digital Signature Verification: Success!");
        else
            System.out.println("Digital Signature Verification: Failed!");
    }
}