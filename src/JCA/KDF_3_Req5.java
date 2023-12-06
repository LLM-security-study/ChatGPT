import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class KDF_3_Req5 {
	public static void main(String[] args) throws Exception {
		String password = "your_password";
		byte[] salt = new byte[16]; // should be generated securely and stored

		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
		byte[] hash = factory.generateSecret(spec).getEncoded();

		System.out.println("Password-based key: " + Arrays.toString(hash));
	}
}