import java.security.*;

public class digital_signature_2_Req9 {

    public static void main(String[] args) throws Exception {
        // Input the data to be signed
        byte[] data = "message".getBytes("UTF8");

        // Generate a RSA key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Sign the data
        Signature rsa = Signature.getInstance("SHA1withRSA");
        rsa.initSign(privateKey);
        rsa.update(data);
        byte[] sig = rsa.sign();

        // Verify the signature
        rsa.initVerify(publicKey);
        rsa.update(data);
        boolean verifies = rsa.verify(sig);

        System.out.println("Signature:" + new String(sig, "UTF8"));
        System.out.println("Verification result: " + verifies);
    }
}