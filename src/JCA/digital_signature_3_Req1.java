import java.security.*;
import java.util.Base64;

public class digital_signature_3_Req1 {

    public static void main(String[] args) {
        String message = "My secret message";

        try {
            // Generate a key pair
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
            SecureRandom random = SecureRandom.getInstanceStrong();
            keyGen.initialize(1024, random);
            KeyPair pair = keyGen.generateKeyPair();
            PrivateKey priv = pair.getPrivate();
            PublicKey pub = pair.getPublic();

            // Get a signature object and initialize it with the private key
            Signature signer = Signature.getInstance("SHA256withDSA");
            signer.initSign(priv);

            // Feed the data to be signed to the Signature object
            signer.update(message.getBytes("UTF-8"));

            // Perform the signing operation
            byte[] signature = signer.sign();

            // Convert the signature to Base64 encoded string and print it
            String stringSignature = Base64.getEncoder().encodeToString(signature);
            System.out.println("Signature: " + stringSignature);

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | UnsupportedEncodingException e) {
            System.err.println("Error signing the message: " + e.getMessage());
        }
    }

}