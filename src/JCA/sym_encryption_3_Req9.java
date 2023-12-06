import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class sym_encryption_3_Req9 {
    private static final String secretKey = "1234567890123456"; // 16 chars = 128 bit

    public static void main(String[] args){
        String cipherText = "I3fK6r+Vt5yGAa+K7gR8Hg=="; // your encrypted string(text)
        String decryptedText = decrypt(cipherText, secretKey);

        System.out.println("Decrypted text: " + decryptedText);
    }

    public static String decrypt(String cipherText, String secretKey) {
        String decryptedText = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decode = java.util.Base64.getDecoder().decode(cipherText);
            decryptedText = new String(cipher.doFinal(decode));

        } catch (Exception e) {
           e.printStackTrace();
        }

        return decryptedText;
    }
}