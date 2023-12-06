import java.security.MessageDigest;
import java.util.Scanner;
import java.security.NoSuchAlgorithmException;

public class hash_3_Req29 {

    public static void main(String args[]) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the message: ");
        String message = scanner.nextLine();
        
        System.out.println("Hash code for given message is : " + computeSHA256(message));
    }

    private static String computeSHA256(String message) throws NoSuchAlgorithmException{
        // Creating MessageDigest instance for SHA-256
        MessageDigest md = MessageDigest.getInstance("SHA-256"); 

        // Digesting the message and returns byte array
        byte[] messageDigest = md.digest(message.getBytes());

        // Converting byte array into HexString format
        StringBuilder sb = new StringBuilder();
        for (byte b : messageDigest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}