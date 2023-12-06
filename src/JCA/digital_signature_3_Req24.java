import java.nio.charset.StandardCharsets;
import java.security.*;

public class digital_signature_3_Req24 {
    public static void main(String[] args) throws Exception {
        //Initial data
        String data = "Input message";

        //Generate a key-pair
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();

        //Sign Data
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(pair.getPrivate());
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        signature.update(bytes);
        byte[] digitalSignature = signature.sign();

        //Check validity
        signature.initVerify(pair.getPublic());
        signature.update(bytes);

        //Print the results
        System.out.println("Is the signature valid? " + signature.verify(digitalSignature));
    }
}