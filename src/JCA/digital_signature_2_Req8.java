import java.security.*;

public class digital_signature_2_Req8 {
    public static void main(String[] args) {
        try {
            // Generation of key pair 
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);
            KeyPair pair = keyGen.generateKeyPair();
            PrivateKey priv = pair.getPrivate();
            PublicKey pub = pair.getPublic();

            //Get a Signature object and initialize it with the private key
            Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
            dsa.initSign(priv);

            // Supply the Signature object with data 
            String data = "Data to be signed and verified";
            byte[] dataBytes = data.getBytes();
            dsa.update(dataBytes);

            // Generate Signature
            byte[] realSig = dsa.sign();

            //Initialize the Signature object for verification and update it with the data to be verified
            Signature sigToVerify = Signature.getInstance("SHA1withDSA", "SUN");
            sigToVerify.initVerify(pub);
            sigToVerify.update(dataBytes);

            // Verify the signature
            boolean verifies = sigToVerify.verify(realSig);
            System.out.println("Verification: " + verifies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}