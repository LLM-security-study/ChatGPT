import java.security.*;
import java.util.*;

class digital_signature_3_Req8 {
    public static void main(String args[]) {
        try {
            // Generate a key pair 
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            SecureRandom randomNumber = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, randomNumber);
            KeyPair pair = keyGen.generateKeyPair();
            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();

            // Create a Signature object and initialize it with the private key
            Signature dsa = Signature.getInstance("SHA256withDSA", "SUN"); 
            dsa.initSign(privateKey);

            // Prepare the message
            String message = "This is the message to be signed";
            byte[] messageBytes = message.getBytes();
              
            // Feed the data to the Signature object
            dsa.update(messageBytes);

            // Now that all the data to be signed has been read in, generate a signature for it
            byte[] signature = dsa.sign();

            // Print the signature
            System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));

            // Verify the signature
            dsa.initVerify(publicKey);
            dsa.update(messageBytes);
            boolean verifies = dsa.verify(signature);
            System.out.println("Signature verifies: " + verifies);
        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }
    }
}