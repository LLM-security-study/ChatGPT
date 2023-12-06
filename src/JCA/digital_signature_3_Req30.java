import java.security.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class digital_signature_3_Req30 {
    public static void main(String[] args) {
        try {
            //Get the instance of the KeyPairGenerator
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            
            //Initialize the KeyPairGenerator
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);
            
            //Generate the Pair of keys
            KeyPair pair = keyGen.generateKeyPair();
            PrivateKey priv = pair.getPrivate();
            PublicKey pub = pair.getPublic();
            
            //Get instance of signature
            Signature dsa = Signature.getInstance("SHA1withDSA", "SUN"); 
            
            //Initialize the signature
            dsa.initSign(priv);
            
            //Update and sign the data
            String msg = "This is a message to be signed.";
            byte[] strByte = msg.getBytes(StandardCharsets.UTF_8);
            dsa.update(strByte);
            
            //Now that all the data to be signed has been read in, generate a signature for it
            byte[] realSig = dsa.sign();

            //if verification is needed:
            //init verification with public key
            dsa.initVerify(pub);
            
            //update verification with data to be verified
            dsa.update(strByte);

            //verify the signature
            boolean verifies = dsa.verify(realSig);
            System.out.println("Signature verifies: " + verifies);

            System.out.println("Signature: " + Base64.getEncoder().encodeToString(realSig));
        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }
    }
}