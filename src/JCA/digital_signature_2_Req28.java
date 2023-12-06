import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class digital_signature_2_Req28 {
  public static void main(String[] args) throws Exception {
    String data = "Sign this data";

    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    keyGen.initialize(2048);

    KeyPair pair = keyGen.generateKeyPair();
    PrivateKey privateKey = pair.getPrivate();
    PublicKey publicKey = pair.getPublic();

    Signature signature = Signature.getInstance("SHA256withRSA");
    signature.initSign(privateKey);
    signature.update(data.getBytes());

    byte[] digitalSignature = signature.sign();

    // sends publicKey, data, digitalSignature to the receiver

    // receiver verifies the signature with the sender's public key
    Signature verification = Signature.getInstance("SHA256withRSA");
    verification.initVerify(publicKey);
    verification.update(data.getBytes());

    if(verification.verify(digitalSignature)) {
        System.out.println("Signature is verified");
    } else {
        System.out.println("Signature not verified");
    }
  }
}