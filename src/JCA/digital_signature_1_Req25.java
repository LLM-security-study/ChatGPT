import java.security.*;

public class digital_signature_1_Req25 {

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA"); 
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        keyGen.initialize(1024, random); 
        return keyGen.genKeyPair();
    }

    private static byte[] signMessage(PrivateKey privateKey, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signer = Signature.getInstance("SHA1withDSA"); 
        signer.initSign(privateKey);
        signer.update(message); 
        return signer.sign();
    }
    
    private static boolean verifyMessage(PublicKey publicKey, byte[] message, byte[] signed) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature verifier = Signature.getInstance("SHA1withDSA"); 
        verifier.initVerify(publicKey);
        verifier.update(message);
        return verifier.verify(signed);
    }
    
    public static void main(String[] args) {
        try {
            KeyPair pair = generateKeyPair();
            String message = "This is a test message";
            byte[] signed = signMessage(pair.getPrivate(), message.getBytes());

            System.out.println("Signature verification: "+verifyMessage(pair.getPublic(), message.getBytes(), signed));
       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}