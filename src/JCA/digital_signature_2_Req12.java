import java.security.*;

public class digital_signature_2_Req12 {
    public static void main(String[] args) throws Exception {
        final String message = "This is a secret message";

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair myPair = kpg.generateKeyPair();

        Sender mySender = new Sender();
        byte[] digitalSignature = mySender.createDigitalSignature(message, myPair.getPrivate());

        Receiver myReceiver = new Receiver();
        boolean isVerified = myReceiver.verifyDigitalSignature(message, digitalSignature, myPair.getPublic());
        System.out.println("The signature is verified : " + isVerified);
    }
}

class Sender {
    byte[] createDigitalSignature(String data, PrivateKey privateKey) throws Exception {
        Signature rsa = Signature.getInstance("SHA256withRSA");
        rsa.initSign(privateKey);
        rsa.update(data.getBytes());
        return rsa.sign();
    }
}

class Receiver {
    boolean verifyDigitalSignature(String data, byte[] digitalSignature, PublicKey publicKey) throws Exception {
        Signature rsa = Signature.getInstance("SHA256withRSA");
        rsa.initVerify(publicKey);
        rsa.update(data.getBytes());
        return rsa.verify(digitalSignature);
    }
}