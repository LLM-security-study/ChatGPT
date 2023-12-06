import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class digital_signature_2_Req21 {

    public static void main(String[] args) {
        try {
            // Generate a KeyPair (public and private keys)
            KeyPair keyPair = generateKeyPair();
            
            // The original data
            String originalData = "Hello, World!";

            // Generate digital signature
            byte[] digitalSignature = signData(originalData.getBytes(), keyPair.getPrivate());

            // Verify the digital signature
            boolean isVerified = verifySignature(originalData.getBytes(), digitalSignature, keyPair.getPublic());

            if (isVerified) {
                System.out.println("Digital Signature Verified.");
            } else {
                System.out.println("Cannot Verify Digital Signature.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        // Instance of key pair generator
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");

        // Initialize with a key size
        kpg.initialize(2048);
        return kpg.genKeyPair();
    }

    public static byte[] signData(byte[] data, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // Get instance of signature
        Signature rsa = Signature.getInstance("SHA256withRSA");

        // Initialize the signature with the private key
        rsa.initSign(privateKey);

        // Update the data to be signed
        rsa.update(data);

        // Sign the data and return it
        return rsa.sign();
    }

    public static boolean verifySignature(byte[] originalData, byte[] signature, PublicKey publicKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // Get instance of signature
        Signature rsa = Signature.getInstance("SHA256withRSA");

        // Initialize the signature with the public key
        rsa.initVerify(publicKey);

        // Provide the original message to the Signature object
        rsa.update(originalData);

        // Verify the signature and return the result
        return rsa.verify(signature);
    }

}