import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class KDF_2_Req28 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter password: ");

        String password = input.nextLine();
        generateKey(password);
    }

    public static void generateKey(String password) {
        try {
            byte[] salt = new byte[16];
            new SecureRandom().nextBytes(salt);

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

            SecretKeySpec key = new SecretKeySpec(hashedPassword, "AES");
            
            System.out.print("Generated secret key: ");
            System.out.println(DatatypeConverter.printHexBinary(key.getEncoded()));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}