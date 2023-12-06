import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class sym_encryption_3_Req20 {
    public static void main (String[] args) {
        try {
            // Initializing Key Generator
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            keygen.init(128);
            SecretKey aesKey = keygen.generateKey();

            // Path to the file which needs to be encrypted
            String filePath = "src/inputFile.txt";
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));

            // Encrypt data
            Cipher encryptCipher = Cipher.getInstance("AES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encryptedFileContent = encryptCipher.doFinal(fileContent);

            FileOutputStream encryptedFileOutStream = new FileOutputStream("src/encryptedFile.txt");
            encryptedFileOutStream.write(encryptedFileContent);
            encryptedFileOutStream.close();

            // Decrypt data
            Cipher decryptCipher = Cipher.getInstance("AES");
            decryptCipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] decryptedFileContent = decryptCipher.doFinal(encryptedFileContent);

            FileOutputStream decryptedFileOutStream = new FileOutputStream("src/decryptedFile.txt");
            decryptedFileOutStream.write(decryptedFileContent);
            decryptedFileOutStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}