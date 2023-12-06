import java.security.*;
import java.util.Arrays;

public class digital_signature_1_Req15 {

    private static PrivateKey privateKey;
    private static PublicKey publicKey;

    public static void main(String[] args) throws Exception{
        
        // Generate public and private keys
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA","SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);

        KeyPair pair = keyGen.generateKeyPair();
        privateKey = pair.getPrivate();
        publicKey = pair.getPublic();

        // Original data
        byte[] originalData = "sample message".getBytes("UTF8");

        // Sign
        byte[] digitalSignature = sign(originalData);

        // Verify
        boolean isValid = verify(originalData, digitalSignature);
        System.out.println("Signature valid: " + isValid);
    }

    public static byte[] sign(byte[] data) throws Exception {
        Signature dsa = Signature.getInstance("SHA256withDSA", "SUN"); 
        dsa.initSign(privateKey);
        dsa.update(data);

        /* Now that all the data to be signed has been read in, 
            generate a signature for it */
        byte[] signature = dsa.sign();

        return signature;
    }

    public static boolean verify(byte[] data, byte[] signature) throws Exception {
        Signature sig = Signature.getInstance("SHA256withDSA", "SUN");
        sig.initVerify(publicKey);
        sig.update(data);

        /* Verify the signature */
        return sig.verify(signature);
    }

}