import java.security.*;

public class digital_signature_2_Req27 {
    public static void main(String[] args) throws Exception {
        // Creating KeyPair generator object
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);

        // Generate the pair of keys
        KeyPair pair = keyPairGen.generateKeyPair();

        // Getting the public key from the key pair
        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate(); 

        // Creating a Signature object
        Signature sign = Signature.getInstance("SHA256withRSA");

        // Initialize the signature
        sign.initSign(privateKey);

        byte[] bytes = "Hello, welcome to Java programming!".getBytes();

        // Add data to the signature
        sign.update(bytes);

        // Compute the signature
        byte[] signature = sign.sign();

        // Initialize the same signature object with the public key
        sign.initVerify(publicKey);

        // Supply the Signature Object With the Data to be verified
        sign.update(bytes);

        // Verify the signature
        boolean valid = sign.verify(signature);
        System.out.println("Signature valid: " + valid);
    }
}