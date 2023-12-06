import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Arrays;

public class digital_signature_2_Req10 {

    public static void main(String[] args) throws Exception {
        
        // Generate Public and Private Keys
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA", "SUN");
        keyPairGen.initialize(1024);
        KeyPair pair = keyPairGen.generateKeyPair();
        PrivateKey privKey = pair.getPrivate(); // Private Key
        PublicKey publKey = pair.getPublic(); // Public Key

        // Data to Sign
        byte[] data = "This is test data".getBytes(); 

        // Sign the data
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
        dsa.initSign(privKey);
        dsa.update(data);
        byte[] signature = dsa.sign(); // Signature

        // Signature Verification using public key
        dsa.initVerify(publKey);
        dsa.update(data);
        boolean isVerified = dsa.verify(signature);
        System.out.println("Is signature verified? " + isVerified);
    }
}