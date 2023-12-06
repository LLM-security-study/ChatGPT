import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Scanner;

public class KDF_2_Req6 {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a password to create a secret key:");

        String password = scanner.nextLine();

        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = password.getBytes("UTF-8");
        key = sha.digest(key);
        SecretKey secretKey = new SecretKeySpec(key, "AES");

        System.out.println("The secret key is:");
        System.out.println(convertToHex(secretKey.getEncoded()));
    }

    public static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }
}