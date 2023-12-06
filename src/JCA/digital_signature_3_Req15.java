import java.security.*;

public class digital_signature_3_Req15 {
  public static void main(String[] args) throws Exception {
    // Define the original message
    String originalMessage = "This is the original message";

    // Generate a KeyPair Generator for DSA keys
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");

    // Initialize the KeyPair Generator
    SecureRandom random = SecureRandom.getInstanceStrong();
    keyGen.initialize(1024, random);

    // Generate the Pair of keys
    KeyPair pair = keyGen.generateKeyPair();
    PrivateKey priv = pair.getPrivate();
    PublicKey pub = pair.getPublic();

    // Get a Signature object and initialize it with the private key
    Signature dsa = Signature.getInstance("SHA256withDSA"); 
    dsa.initSign(priv);

    // Update and sign the data
    byte[] message = originalMessage.getBytes("UTF8");
    dsa.update(message);
    
    // Now that all the data is ready, perform the digital signature
    byte[] signature = dsa.sign();

    // print the signature
    System.out.println("Digital Signature: "+bytesToHex(signature));
  }

  // Function to convert byte array to hexadecimal
  public static String bytesToHex(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
        sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }
}