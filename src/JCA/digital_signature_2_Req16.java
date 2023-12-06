import java.security.*;

public class digital_signature_2_Req16 {
    public static void main(String[] args) {
        try {
            //generate key pair
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            keyGen.initialize(1024, random);
            KeyPair pair = keyGen.generateKeyPair();
            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();

            //data to sign
            byte[] data = "This message will be signed".getBytes("UTF8");

            //signing data
            Signature signer = Signature.getInstance("SHA1withDSA");
            signer.initSign(privateKey);
            signer.update(data);
            byte[] signature = signer.sign();

            //verify signature
            signer.initVerify(publicKey);
            signer.update(data);

            System.out.println("Signature " + (signer.verify(signature) ? "verified" : "not verified"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}