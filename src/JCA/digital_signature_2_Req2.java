import java.security.*;
import java.util.Base64;

public class digital_signature_2_Req2 {

    public static void main(String[] args) throws Exception {
        String originalData = "Hello, world!"; 

        KeyPair keyPair = generateKeyPair();

        String signature = sign(originalData, keyPair.getPrivate());

        System.out.println("Signature: " + signature);
      
        boolean isValid = verify(originalData, signature, keyPair.getPublic());

        System.out.println("Signature is valid: " + isValid);
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    public static String sign(String data, PrivateKey privateKey) throws Exception {
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(privateKey);
        signer.update(data.getBytes());
        byte[] signature = signer.sign();
        return Base64.getEncoder().encodeToString(signature);
    }

    public static boolean verify(String data, String signature, PublicKey publicKey) throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(publicKey);
        sig.update(data.getBytes());

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        return sig.verify(signatureBytes);
    }
}