import java.security.*;
import java.util.Arrays;

public class digital_signature_3_Req19 {
    public static void main(String[] args){
        try {
            // Input message
            String message = "This is a demo message";

            KeyPair pair = generateKeyPair();
            byte[] signature = signMessage(pair.getPrivate(), message);

            if (verifySignature(pair.getPublic(), signature, message)) {
                System.out.println("The signature has been verified and is valid.");
            } else {
                System.out.println("The signature isn't valid.");
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        SecureRandom random = new SecureRandom();
        keyGen.initialize(512, random);
        KeyPair pair = keyGen.generateKeyPair();
        return pair;
    }

    public static byte[] signMessage(PrivateKey privateKey, String message) throws Exception{
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
        dsa.initSign(privateKey);

        byte[] strByte = message.getBytes();
        dsa.update(strByte);

        byte[] realSig = dsa.sign();
        return realSig;
    }

    public static boolean verifySignature(PublicKey publicKey, byte[] signature, String message) throws Exception {
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initVerify(publicKey);

        byte[] messageBytes = message.getBytes();
        sig.update(messageBytes);

        return sig.verify(signature);
    }
}