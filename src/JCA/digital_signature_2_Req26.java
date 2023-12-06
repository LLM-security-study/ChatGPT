import java.security.*;

public class digital_signature_2_Req26 {
    public static void main(String[] args) {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
            SecureRandom secRandom = new SecureRandom();

            keyPairGen.initialize(2048, secRandom);
            KeyPair pair = keyPairGen.generateKeyPair();

            PrivateKey priv = pair.getPrivate();
            PublicKey pub = pair.getPublic();
            
            Signature ecdsaSign = Signature.getInstance("SHA256withDSA");
            ecdsaSign.initSign(priv);

            byte[] strByte = "Hello, world!".getBytes();
            ecdsaSign.update(strByte);

            byte[] signature = ecdsaSign.sign();
            System.out.println("Signature Value : " + new String(signature, "UTF8"));

            Signature signatureVerify = Signature.getInstance("SHA256withDSA");
            signatureVerify.initVerify(pub);

            signatureVerify.update(strByte);
            boolean check = signatureVerify.verify(signature);
            System.out.println("Signature Verify : " + check);
        } 
        catch(Exception e) {
            System.out.println("Exception : " + e);
        }
    }
}