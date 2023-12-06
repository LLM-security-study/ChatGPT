import java.security.*;
import java.util.Arrays;

public class digital_signature_1_Req18 {

    static PublicKey publicKey;
    static PrivateKey privateKey;

    public static void main(String[] args) {
        
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

            keyGen.initialize(1024, random);
            
            KeyPair pair = keyGen.generateKeyPair();
            publicKey = pair.getPublic();
            privateKey = pair.getPrivate();

            //message
            byte[] message = "This is a test message.".getBytes("UTF8");

            //Fire signing
            byte[] digitalSignature = signMessage(message);

            //Fire verification
            boolean isVerified = verifySignature(message, digitalSignature);
            
            System.out.println("Signature Verification: " + isVerified);
            
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
    }


    private static byte[] signMessage(byte[] message) {
        try {
            Signature sig = Signature.getInstance("SHA1withDSA");
            sig.initSign(privateKey);
            sig.update(message);
            return sig.sign();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean verifySignature(byte[] message, byte[] digitalSignature) {
        try {
            Signature sig = Signature.getInstance("SHA1withDSA");
            sig.initVerify(publicKey);
            sig.update(message);
            return sig.verify(digitalSignature);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}