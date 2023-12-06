import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

class sym_encryption_3_Req18 {
	public static void main(String[] args) throws Exception {

        // Generate secret key
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey secretKey = keyGenerator.generateKey();

		// Original Data
		String data = "SecureData";
		System.out.println("Original Data: " + data);

		// Encryption
		Cipher aesCipherEncrypt = Cipher.getInstance("AES");
		aesCipherEncrypt.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] byteCipherText = aesCipherEncrypt.doFinal(data.getBytes());
		String strCipherText = Base64.getEncoder().encodeToString(byteCipherText);
		System.out.println("Encrypted Data: " + strCipherText);

		// Decryption
		Cipher aesCipherDecrypt = Cipher.getInstance("AES");
		aesCipherDecrypt.init(Cipher.DECRYPT_MODE, secretKey, aesCipherEncrypt.getParameters());
		byte[] byteDecipherText = aesCipherDecrypt.doFinal(byteCipherText);
		String strDecipherText = new String(byteDecipherText);
		System.out.println("Decrypted Data: " + strDecipherText);
	}
}