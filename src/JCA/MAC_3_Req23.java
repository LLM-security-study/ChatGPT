import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

class MAC_3_Req23 {
    public static void main(String[] args) {
        String secretKey = "MySecretKey";
        String message = "Hello, World!";
        
        String mac = calculateHMAC(secretKey, message);
        System.out.println("MAC: " + mac);
    }

    private static String calculateHMAC(String secret, String message){
        String hash = "";
       
        try {
            Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            hmacSHA256.init(secretKeySpec);
            byte[] bytes = hmacSHA256.doFinal(message.getBytes(StandardCharsets.UTF_8));
            hash = bytesToHex(bytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        
        return hash;
    }
    
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}