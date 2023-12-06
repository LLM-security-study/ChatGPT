import java.security.*;
import java.util.Arrays;

public class digital_signature_2_Req14 {
    public static void main(String[] args) throws Exception {

        // Generate a key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024); // you can increase the key size for stronger security
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Original data
        String originalData = "Hello, World!";

        // Sign the data
        Signature rsaSig = Signature.getInstance("SHA256withRSA");
        rsaSig.initSign(privateKey);
        rsaSig.update(originalData.getBytes());
        byte[] signature = rsaSig.sign();

        System.out.println("Signature: " + Arrays.toString(signature));

        // To verify a signature
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(originalData.getBytes());

        if (publicSignature.verify(signature)) {
            System.out.println("Signature verified");
        } else {
            System.out.println("Signature verification failed");
        }
    }
}