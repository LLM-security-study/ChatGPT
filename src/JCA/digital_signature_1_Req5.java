import java.security.*;

public class digital_signature_1_Req5 {
    public static void main(String[] args) {
        //No keys are provided, this code is incomplete.
    }

    public static byte[] signData(byte[] data, PrivateKey key) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(key);
        signer.update(data);
        return (signer.sign());
    }

    public static boolean verifySignature(byte[] data, byte[] signature, PublicKey key) throws NoSuchAlgorithmException, 
                                                                                              InvalidKeyException, 
                                                                                              SignatureException {
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initVerify(key);
        signer.update(data);
        return (signer.verify(signature));
    }
}