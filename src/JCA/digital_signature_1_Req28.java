import java.security.*;
import java.util.Base64;

public class digital_signature_1_Req28 {

    public static void main(String[] args) throws Exception {
        String message = "Hello, World!";

        // Generate the key pair
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyPairGen.initialize(1024, random);
        KeyPair pair = keyPairGen.generateKeyPair();

        // Sign
        String signature = sign(message, pair.getPrivate());
        System.out.println("Signature: " + signature);

        // Verify
        boolean isValid = verify(message, signature, pair.getPublic());
        System.out.println("Signature is valid: " + isValid);
    }

    public static String sign(String data, PrivateKey key) throws Exception {
        Signature signer = Signature.getInstance("SHA256withDSA");
        signer.initSign(key);
        signer.update(data.getBytes());
        byte[] signature = signer.sign();
        return Base64.getEncoder().encodeToString(signature);
    }

    public static boolean verify(String data, String signature, PublicKey key) throws Exception {
        Signature signer = Signature.getInstance("SHA256withDSA");
        signer.initVerify(key);
        signer.update(data.getBytes());
        byte[] signatureBytes = Base64.getDecoder().decode(signature);
        return signer.verify(signatureBytes);
    }
}