import java.util.Scanner;
import java.security.MessageDigest;

public class hash_3_Req3 {
public static void main(String a[]) {
    try {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = msgDigest.digest(input.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        System.out.println("Message digest (hash value) : " + hexString.toString());
    }
    catch(Exception ex) {
        throw new RuntimeException(ex);
    }
}
}