import java.security.*;
import java.util.Base64;

public class digital_signature_1_Req20 {
  public static void main(String[] args) throws Exception {
    // Generate a pair of keys
    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
    keyPairGen.initialize(2048);
    KeyPair pair = keyPairGen.generateKeyPair();
    
    String originalData = "Important message";
    
    System.out.println("Original Message : "+originalData);
    
    // Sign the data
    byte[] signature = signData(originalData, pair.getPrivate());
    System.out.println("Signature : "+ Base64.getEncoder().encodeToString(signature));
    
    // Verify the signature
    boolean status = verifySignature(originalData, signature, pair.getPublic());
    System.out.println("Status of verification : "+ status);
  }

  // Sign data using a secret (private key)
  public static byte[] signData(String data, PrivateKey privateKey) throws Exception {
    Signature rsa = Signature.getInstance("SHA256withDSA");
    rsa.initSign(privateKey);
    rsa.update(data.getBytes());
    return rsa.sign();
  }

  // Verify signed data using a public key
  public static boolean verifySignature(String data, byte[] signature, PublicKey publicKey) throws Exception {
    Signature sig = Signature.getInstance("SHA256withDSA");
    sig.initVerify(publicKey);
    sig.update(data.getBytes());
    return sig.verify(signature);
  }
}