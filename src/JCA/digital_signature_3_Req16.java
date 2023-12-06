import java.security.*;

public class digital_signature_3_Req16 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
      
        // generate key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();


        // get text
        String text = "This is a message to be signed and verified";

        // create a signature object
        Signature signature = Signature.getInstance("SHA256withRSA");

        // initialize the signature object with the private key
        signature.initSign(priv);

        // add data to the signature
        signature.update(text.getBytes());

        // sign the data and get the result
        byte[] signed = signature.sign();

        System.out.println("Signed message: " + new String(signed));

        // verify signature
        signature.initVerify(pub);
        signature.update(text.getBytes());
        boolean bool = signature.verify(signed);
        System.out.println("Verification: " + bool);
    }
}