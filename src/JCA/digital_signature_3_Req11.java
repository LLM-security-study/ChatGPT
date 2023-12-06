import java.security.*;

public class digital_signature_3_Req11 {
    public static void main(String[] args) throws Exception{
        String message = "This is the message to be signed";

        // Generate key pair
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();

        // Get private and public keys
        PrivateKey privKey = pair.getPrivate();
        PublicKey pubKey = pair.getPublic();

        // Sign the message
        byte[] signedMessage = signMessage(message, privKey);

        // check if the message is signed properly
        boolean isValid = verifySignature(message, signedMessage, pubKey);
        System.out.println("Signature valid? " + isValid);
    }

    public static byte[] signMessage(String message, PrivateKey privateKey) throws Exception {
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initSign(privateKey);
        sign.update(message.getBytes());
        return sign.sign();
    }

    public static boolean verifySignature(String message, byte[] signature, PublicKey publicKey) throws Exception {
        Signature verifySign = Signature.getInstance("SHA256withDSA");
        verifySign.initVerify(publicKey);
        verifySign.update(message.getBytes());
        return verifySign.verify(signature);
    }
}