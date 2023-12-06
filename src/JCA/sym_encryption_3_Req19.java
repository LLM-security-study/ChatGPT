import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

class sym_encryption_3_Req19 {
    private static final String AES = "AES";

    public static void main(String args[]) {
        try {
            String secretKey = "ssdkF$HUiy77#js";
            String encryptedString = "5UJiFctbmgbDoLXmpL12mkno8HT4Lv8dlat8FxR2GOc=";

            String decryptedString = decrypt(encryptedString, secretKey);
            System.out.println("Decrypted string: " + decryptedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String decrypt(String strToDecrypt, String secret) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}