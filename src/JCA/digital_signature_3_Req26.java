import java.security.*;
import java.util.Base64;

public class digital_signature_3_Req26 {
    public static void main(String[] args) {
        try {
            // generate a RSA keypair, this will be the key pair that our client
            // and server uses
            KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // let's sign some message
            String msg = "My test message";
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(msg.getBytes());

            byte[] signatureBytes = signature.sign();
            String signedMsg = Base64.getEncoder().encodeToString(signatureBytes);
            System.out.println("Signed Message: " + signedMsg);

            // let's verify the signed message
            Signature signature1 = Signature.getInstance("SHA256withRSA");
            signature1.initVerify(publicKey);
            signature1.update(msg.getBytes());

            System.out.println("Is the signature valid? " + signature1.verify(signatureBytes));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}