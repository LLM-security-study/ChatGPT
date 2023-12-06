import java.security.*;

public class digital_signature_2_Req25 {
    public static void main(String[] args) throws Exception {
        // KeyPairGenerator class is used to generate pairs of public and private keys.
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        String str = "This is the text to be signed.";
        byte[] realSig = signData(str.getBytes(), priv);

        boolean status = verifySig(str.getBytes(),pub,realSig);
        System.out.println("Signature verification status: "+ status);
    }

    static byte[] signData(byte[] data, PrivateKey priv) throws Exception {
        Signature rsa = Signature.getInstance("SHA1withDSA");
        rsa.initSign(priv);

        rsa.update(data);

        return rsa.sign();
    }

    static boolean verifySig(byte[] data, PublicKey pub, byte[] sig) throws Exception {
        Signature sign = Signature.getInstance("SHA1withDSA");
        sign.initVerify(pub);

        sign.update(data);

        return sign.verify(sig);
    }
}