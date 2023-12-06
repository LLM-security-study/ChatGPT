import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

class sym_encryption_CBC_3_Req17 {
    public static void main(String[] args) throws Exception {
        String key = "abcdabcdabcdabcd"; // 128 bit key
        String initVector = "randominitvector"; // 16 bytes IV

        String encrypted = "TpZGWjQ/2nS3hbcINSQ6KQ=="; // Your encrypted text here

        System.out.println(decrypt(key, initVector, encrypted));
    }

    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}