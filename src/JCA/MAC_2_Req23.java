import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MAC_2_Req23 {
    private static final String HMAC_ALGO = "HmacSHA256";

    public static void main(String[] args) throws Exception {
        String message = "This is a message for MAC check";

        // Define a key in bytes.
        byte[] keyBytes = new byte[] { (byte)0x3a, (byte)0x82, (byte)0x2c, (byte)0x3, (byte)0x96, 
                                       (byte)0x96, (byte)0xaf, (byte)0xdb, (byte)0x63, (byte)0x0, 
                                       (byte)0x3a, (byte)0x92, (byte)0xfc, (byte)0x49, (byte)0x92};
        performMAC(message, keyBytes);
    }

    private static void performMAC(String message, byte[] keyBytes) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(HMAC_ALGO);

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, HMAC_ALGO);
        mac.init(secretKeySpec);

        byte[] macRes = mac.doFinal(message.getBytes());
        System.out.println(bytesToHex(macRes));
    }
  
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));  
        }
        return sb.toString();
    }
}