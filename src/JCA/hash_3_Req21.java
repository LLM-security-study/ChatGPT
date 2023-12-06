import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Scanner;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class hash_3_Req21 {
    public static void main(String args[]) {
        Security.addProvider(new BouncyCastleProvider());
        print("BouncyCastle provider added.");

        print("Data to hash: ");
        hashData("Test Data");
    }

    private static void print(Object text) {
        System.out.println(String.valueOf(text));
    }

    private static void hashData(String data) {
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
            byte[] dataBytes = data.getBytes();
            msgDigest.update(dataBytes);

            byte[] mdbytes = msgDigest.digest();
            print("Digest in hex format : " + Hex.toHexString(mdbytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}