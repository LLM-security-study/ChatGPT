import java.nio.charset.Charset;
import java.security.*;

public class digital_signature_1_Req21 {
    public static void main(String[] args) throws Exception {
        // Generate the KeyPair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024, new SecureRandom());
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        // Sign the message
        String message = "This is a secret message!";
        byte[] signatureBytes = sign(message, privateKey);
        
        // verify the signature
        boolean verified = verify(message, signatureBytes, publicKey);
        System.out.println("Signature Verified: " + verified);
    }

    public static byte[] sign(String message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes(Charset.defaultCharset()));
       
        return signature.sign();
    }

    public static boolean verify(String message, byte[] signatureBytes, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message.getBytes(Charset.defaultCharset()));
       
        return signature.verify(signatureBytes);
    }
}