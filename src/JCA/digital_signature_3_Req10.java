import java.security.*;

public class digital_signature_3_Req10 {
    public static void main(String[] args){
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");

            keyGen.initialize(1024, random);

            KeyPair pair = keyGen.generateKeyPair();
            PrivateKey priv = pair.getPrivate();
            PublicKey pub = pair.getPublic();

            Signature dsa = Signature.getInstance("SHA256withDSA", "SUN"); 

            dsa.initSign(priv);

            String message = "This is a message to be signed";
            byte[] messageBytes = message.getBytes();

            dsa.update(messageBytes);

            byte[] signature = dsa.sign();

            // output signature
            System.out.println("Signature: " + new String(signature, "UTF8"));

        } catch (Exception e){
            System.err.println("Caught exception " + e.toString());
        }
    }
}