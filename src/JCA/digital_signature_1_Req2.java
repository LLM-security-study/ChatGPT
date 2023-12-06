import java.security.*;

public class digital_signature_1_Req2 {
    public static void main(String[] args) throws Exception {
        // The original data
        String originalData = "Hello, World!";
        byte[] bytes = originalData .getBytes("UTF8");
   
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
   
        Signature signature = Signature.getInstance("SHA256WithRSA");
   
        // Signing
        byte[] digitalSignature = signBytes(bytes, keyPair.getPrivate(), signature);
        System.out.println(new String(digitalSignature, "UTF8"));
   
        // Verifying
        boolean isVerified = verifySignature(bytes, digitalSignature, keyPair.getPublic(), signature);
        System.out.println("Signature verified: " + isVerified);
    }

    public static byte[] signBytes(byte[] data, PrivateKey privateKey, Signature signature) throws Exception {
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    public static boolean verifySignature(byte[] data, byte[] digitalSignature, PublicKey publicKey, Signature signature) throws Exception {
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(digitalSignature);
    }
}