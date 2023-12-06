import java.security.*;

public class digital_signature_2_Req19 {
    public static void main(String[] args) throws Exception{

        // Creating a KeyPairGenerator
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");

        // Initializing the KeyPairGenerator
        keyGen.initialize(1024);

        // Generate the pair of keys
        KeyPair pair = keyGen.generateKeyPair();

        // Getting the private key from the key pair
        PrivateKey priv = pair.getPrivate(); 
        
        // Getting the public key from the key pair
        PublicKey pub = pair.getPublic();       
       
        // Creating a Signature object
        Signature sign = Signature.getInstance("SHA256withDSA");

        // Initializing the signature
        sign.initSign(priv);

        String msg = "Hello World!";

        // Adding data to the signature
        byte[] bytes = msg.getBytes();
        sign.update(bytes);

        // Calculating the signature
        byte[] signature = sign.sign(); 

        // Initializing the same signature object with the public key
        sign.initVerify(pub);

        // Updating the signature
        sign.update(bytes);

        // Verifying the signature
        boolean bool = sign.verify(signature);

        if(bool) {
            System.out.println("Signature verified");
        } else {
            System.out.println("Signature failed");
        }
    }
}