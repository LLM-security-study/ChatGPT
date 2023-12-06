import java.security.*;

public class digital_signature_3_Req4 {
    public static void main(String[] args) throws Exception {
        //Generate KeyPair
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();
        
        //Sign the message
        String msg = "Message to be signed";
        byte[] bytes = msg.getBytes();
      
        Signature sign = Signature.getInstance("SHA256withRSA");
      
        //Initializing the signature
        sign.initSign(pair.getPrivate());
      
        //Adding data to the signature
        sign.update(bytes);
      
        //Calculating the signature
        byte[] signature = sign.sign();
        System.out.println("Signed message: " + new String(signature, "UTF8"));
      
        //Initialising the signature for verification
        sign.initVerify(pair.getPublic());
        sign.update(bytes);
      
        //Verifying the signature
        boolean bool = sign.verify(signature);
        
        if(bool) {
            System.out.println("Signature verified");
        } else {
            System.out.println("Signature failed");
        }
    }
}