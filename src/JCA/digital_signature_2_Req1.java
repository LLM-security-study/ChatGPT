import java.security.*;

public class digital_signature_2_Req1 {

    public static void main(String[] args) {
        try {
            //Generating KeyPair
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);
            KeyPair pair = keyPairGen.generateKeyPair();

            //Data that needs to be signed
            String data = "Data need to be signed and verified";

            //Signing the data
            byte[] digitalSignature = signData(data.getBytes(), pair.getPrivate());

            //Verifying the signature
            boolean verified = verifySignature(data.getBytes(), digitalSignature, pair.getPublic());

            System.out.println(verified ? "Signature is verified" : "Signature not verified");

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            System.err.println("Error during the signature creation/verification");
            e.printStackTrace();
        }
    }

    //The method that signs the data 
    public static byte[] signData(byte[] data, PrivateKey privKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(privKey);
        signer.update(data);
        return (signer.sign());
    }

    //The method that verifies the signed data
    public static boolean verifySignature(byte[] data, byte[] sigBytes, PublicKey pubKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(pubKey);
        signer.update(data);
        return (verifier.verify(sigBytes));
    }
}