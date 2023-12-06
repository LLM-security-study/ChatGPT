import java.security.*;

public class digital_signature_2_Req23{
  
  public static void main(String[] args){
    try {
      // Generate key pair
      KeyPair kp = generateKeyPair();
      
      // Sign data using private key
      byte[] digitalSignature = signData("Hello".getBytes(), kp.getPrivate());
      
      // Verify signature using public key
      boolean isVerified = verifyData("Hello".getBytes(), kp.getPublic(), digitalSignature);
      
      System.out.println("Is Verified: " + isVerified);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // This method generates a KeyPair
  public static KeyPair generateKeyPair() throws Exception {
    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
    kpg.initialize(2048);
    KeyPair kp = kpg.genKeyPair();
    return kp;
  }

  // This method signData
  public static byte[] signData(byte[] data, PrivateKey privKey) throws Exception {
    Signature signature = Signature.getInstance("SHA256withRSA");
    signature.initSign(privKey);
    signature.update(data);
    return(signature.sign());
  }
  
  // This method verifies the data
  public static boolean verifyData(byte[] data, PublicKey pubKey, byte[] signature) throws Exception {
    Signature publicSignature = Signature.getInstance("SHA256withRSA");
    publicSignature.initVerify(pubKey);
    publicSignature.update(data);
    return(publicSignature.verify(signature));
  }
}