import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class MAC_2_Req30 {
    public static void main(String[] args) {
        try {
            String secret = "secretkey";
            String message = "Hello, World!";

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            String hash = DatatypeConverter.printHexBinary(sha256_HMAC.doFinal(message.getBytes()));
            System.out.println(hash);
        } catch (Exception e){
            System.out.println("Error");
        }
    }
}