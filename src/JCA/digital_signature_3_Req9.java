import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class digital_signature_3_Req9 {
    public static void main(String[] args){
        try {
            // Generate key pair
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            KeyPair kp = kpg.generateKeyPair();
            PublicKey pub = kp.getPublic();
            PrivateKey priv = kp.getPrivate();
            
            // Message to sign
            String message = "This is a secret message";
            byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
            
            // Signing
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(priv);
            sign.update(bytes);
            byte[] signatureBytes = sign.sign();
            String signature = Base64.getEncoder().encodeToString(signatureBytes);
            
            // Printing the Signature
            System.out.println("Signature: " + signature);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
    }
}