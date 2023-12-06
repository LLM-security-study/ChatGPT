import java.nio.file.*;
import java.security.*;
import java.security.spec.*;

public class digital_signature_2_Req24 {

    public static void main(String[] args) throws Exception {
        // initialize content
        String content = "Hello, World";

        // load private key
        PrivateKey privateKey = getPrivateKey("path_to_private_key");

        // create signature
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(content.getBytes());
        byte[] digitalSignature = signature.sign();

        // load public key
        PublicKey publicKey = getPublicKey("path_to_public_key");

        // verify signature
        signature.initVerify(publicKey);
        signature.update(content.getBytes());
        boolean isVerified = signature.verify(digitalSignature);

        System.out.println("Digital Signature Verification: " + isVerified);
    }

    private static PrivateKey getPrivateKey(String filename) throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    private static PublicKey getPublicKey(String filename) throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }
}