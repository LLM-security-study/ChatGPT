import java.security.*;
import java.util.Arrays;
import javax.xml.bind.DatatypeConverter;

public class digital_signature_1_Req30 {
    public static void main(String[] args) throws Exception {
        String data = "This is the data to sign";
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initSign(priv);
        sig.update(data.getBytes("UTF8"));
        byte[] signatureBytes = sig.sign();
        System.out.println("Signature:" + DatatypeConverter.printHexBinary(signatureBytes));
        sig.initVerify(pub);
        sig.update(data.getBytes("UTF8"));
        boolean verifies = sig.verify(signatureBytes);
        System.out.println("Signature verifies: " + verifies);
    }
    
    static void signMessage() throws Exception{
       // code for signing a message goes here
    }
    
    static void verifyMessage() throws Exception{
      // code for verifying a message goes here
    }
}