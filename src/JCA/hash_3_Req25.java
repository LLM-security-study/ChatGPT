import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_3_Req25 {
    public static void main(String[] args) {
        String message = "Hello, World!";
        byte[] hashedMessage = hashWithSHA256(message);

        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashedMessage) {
            stringBuilder.append(String.format("%02X ", b));
        }

        System.out.println("Original message: " + message);
        System.out.println("Hashed message: " + stringBuilder.toString());
    }

    private static byte[] hashWithSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(input.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}