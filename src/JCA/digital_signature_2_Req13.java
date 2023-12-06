import java.security.*;
import java.util.Base64;

public class digital_signature_2_Req13{
    public static void main(String[] args) throws Exception{

        String privateKeyContent = "<PLACE THE PRIVATE KEY CONTENT HERE>";
        String publicKeyContent = "<PLACE THE PUBLIC KEY CONTENT HERE>";

        Signature signature = Signature.getInstance("SHA256withRSA");
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyContent);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        // Data to be signed
        String data = "Hello, World!";
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        byte[] digitalSignature = signature.sign();

        // Verification of the signature
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyContent);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        signature.initVerify(publicKey);
        signature.update(data.getBytes());

        boolean isVerified = signature.verify(digitalSignature);

        System.out.println("Is the signature verified?: "+isVerified);

    }
}