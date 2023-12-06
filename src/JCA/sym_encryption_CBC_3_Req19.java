import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.util.*;

public class sym_encryption_CBC_3_Req19 {
    public static void main(String[] args) throws Exception {
        String initialVector= "1234567890123456"; // Should be 16 bytes
        String secretKey= "mysecretkey";
        byte[] encryptedData = "YOUR_ENCRYPTED_DATA"; //replace this with your real encrypted data
        IvParameterSpec iv = new IvParameterSpec(initialVector.getBytes("UTF-8"));
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] original = cipher.doFinal(encryptedData);
        System.out.println(new String(original));
    }
}