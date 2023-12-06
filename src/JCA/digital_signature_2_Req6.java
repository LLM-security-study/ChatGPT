import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class digital_signature_2_Req6 {

    public static void main(String[] args) throws Exception {
        // Generate key pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        KeyPair kp = kpg.generateKeyPair();
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();

        String msg = "Text message for digital signature.";
        byte[] data = msg.getBytes();

        // Perform signing
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(privateKey);
        signer.update(data);
        byte[] digitalSignature = signer.sign();

        // Perform verification
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(publicKey);
        verifier.update(data);

        if (verifier.verify(digitalSignature)) {
            System.out.println("Signature is verified.");
        } else {
            System.out.println("Signature not verified.");
        }
    }

}