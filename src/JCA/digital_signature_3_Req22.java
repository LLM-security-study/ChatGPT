import java.security.*;
import java.util.Arrays;
import sun.misc.*;

// Import the Java standard APIs for implementation

public class digital_signature_3_Req22 {
    public static void main(String[] args) throws Exception {
        // The original data
        String data = "This is a message";

        // Generate public and private keys using RSA
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair keypair = keyGen.genKeyPair();
        PrivateKey privateKey = keypair.getPrivate();
        PublicKey publicKey = keypair.getPublic();

        // Get a signature object using the SHA-256 and RSA combo
        // and sign the data with the private key
        Signature instance = Signature.getInstance("SHA256withRSA");
        instance.initSign(privateKey);
        instance.update(data.getBytes());
        byte[] signature = instance.sign();

        System.out.println("Signature: " + new BASE64Encoder().encode(signature));

        // Verify the signature with the public key
        instance.initVerify(publicKey);
        instance.update(data.getBytes());

        System.out.println("Verification: " + instance.verify(signature));
    }
}