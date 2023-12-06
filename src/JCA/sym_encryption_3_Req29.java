import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class sym_encryption_3_Req29 {
    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = 
        new byte[] { 'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };
    
    public static void main(String[] args) throws Exception {
        
        String encryptedText = "f6vD1QD0ec7EYxPk1bomF5g==";  // replace with actual encrypted text
        String decryptedText = decrypt(encryptedText);

        System.out.println("Encrypted Text : " + encryptedText);
        System.out.println("Decrypted Text : " + decryptedText);
    }

    public static String decrypt(String valueToDecrypt) throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decryptedValue64 = Base64.getDecoder().decode(valueToDecrypt);
        byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
        String decryptedValue = new String(decryptedByteValue, "utf-8");
        
        return decryptedValue;
    }
}