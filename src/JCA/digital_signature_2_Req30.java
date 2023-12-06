import java.security.*;

public class digital_signature_2_Req30 {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public static void main(String[] args) {
        digital_signature_2_Req30 sig = new digital_signature_2_Req30();
        sig.generateKeyPair();
        byte[] data = "Data to be signed".getBytes();

        try {
            byte[] signedData = sig.signData(data);
            sig.verifySignature(data, signedData);
        } catch (Exception e) {
            System.out.println("Error signing data: " + e.getMessage());
        }
    }

    public void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA","SUN");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");

            keyGen.initialize(1024, random);
            KeyPair pair = keyGen.generateKeyPair();

            this.privateKey = pair.getPrivate();
            this.publicKey = pair.getPublic();
        } catch (Exception e) {
            System.out.println("Error generating key pair: " + e.getMessage());
        }
    }

    public byte[] signData(byte[] data) throws Exception {
        Signature dsa = Signature.getInstance("SHA256withDSA", "SUN");
        dsa.initSign(this.privateKey);

        dsa.update(data);
        return dsa.sign();
    }

    public void verifySignature(byte[] data, byte[] signedData) throws Exception {
        Signature sig = Signature.getInstance("SHA256withDSA", "SUN");
        sig.initVerify(this.publicKey);
        sig.update(data);

        if (sig.verify(signedData)) {
            System.out.println("Signature verified");
        } else {
            System.out.println("Signature failed");
        }
    }
}