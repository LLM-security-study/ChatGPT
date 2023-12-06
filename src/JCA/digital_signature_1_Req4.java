import java.security.*;
import java.nio.charset.StandardCharsets;

public class digital_signature_1_Req4 {
  public static void main(String[] args) throws Exception {
    // Generate a key pair
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");  
    keyGen.initialize(1024);
    KeyPair keyPair = keyGen.generateKeyPair();
    
    String message = "Hello, World!";
    
    // Sign the message
    byte[] signatureBytes = signMessage(message, keyPair.getPrivate());
    
    // Verify the message
    boolean isVerified = verifyMessage(message, keyPair.getPublic(), signatureBytes);
    System.out.println("Signature verified: " + isVerified);
  }
  
  public static byte[] signMessage(String message, PrivateKey privateKey) throws Exception {
    Signature signature = Signature.getInstance("SHA256withRSA");
    signature.initSign(privateKey);
    byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
    signature.update(bytes);
    return signature.sign();
  }
  
  public static boolean verifyMessage(String message, PublicKey publicKey, byte[] signatureBytes) throws Exception {
    Signature signature = Signature.getInstance("SHA256withRSA");
    signature.initVerify(publicKey);
    byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
    signature.update(bytes);
    return signature.verify(signatureBytes);
  }
}