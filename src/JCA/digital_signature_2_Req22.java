import java.security.*;
import java.util.Base64;

public class digital_signature_2_Req22 {

    public static void main(String[] args) throws Exception {
        
        // Generate key pair
        KeyPair keyPair = generateKeyPair();
       
        String data = "Data to be signed";
        
        String digitalSignature = signDigitalSignature(data, keyPair.getPrivate());

        verifyDigitalSignature(data, digitalSignature, keyPair.getPublic());

    }

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
        keyPairGen.initialize(2048); 
        return keyPairGen.generateKeyPair();
    }

    private static String signDigitalSignature(String data, PrivateKey privateKey) throws Exception{

        Signature sign = Signature.getInstance("SHA256withDSA");

        sign.initSign(privateKey);
        sign.update(data.getBytes());

        byte[] signature = sign.sign();
        return Base64.getEncoder().encodeToString(signature);
    }

    private static void verifyDigitalSignature(String data, String digitalSignature, PublicKey publicKey) throws Exception {

        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initVerify(publicKey);
        sign.update(data.getBytes());

        boolean verified = sign.verify(Base64.getDecoder().decode(digitalSignature));

        if(verified){
            System.out.println("Data verified.");
        }else{
            System.out.println("Data not verified.");
        }
    }
}