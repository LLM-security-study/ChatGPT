import java.security.*;

public class digital_signature_3_Req18 {

    public static void main(String[] args) {
        try {
            // Generate a KeyPair
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            keyGen.initialize(1024, random);
            KeyPair pair = keyGen.generateKeyPair();
            PrivateKey priv = pair.getPrivate();
            PublicKey pub = pair.getPublic();

            // Get a Signature object and sign the plaintext with the private key
            Signature dsa = Signature.getInstance("SHA256withDSA");
            dsa.initSign(priv);
            String message = "This is a test message";
            System.out.println("Original message: " + message);
            byte[] buffer = message.getBytes();
            dsa.update(buffer, 0, buffer.length);

            // Now that all the data to be signed has been read in
            byte[] realSig = dsa.sign();

            System.out.println("Signature: " + new BigInteger(1, realSig).toString(16));

            // Verify the signature
            dsa.initVerify(pub);
            dsa.update(buffer, 0, buffer.length);

            boolean verifies = dsa.verify(realSig);
            System.out.println("Signature verifies: " + verifies);

        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }
    }
}