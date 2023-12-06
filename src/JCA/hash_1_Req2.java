import java.security.MessageDigest;
import java.util.Scanner;
import java.security.NoSuchAlgorithmException;

public class hash_1_Req2 {
    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter a string:");
        String input = in.nextLine();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation  
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex=Integer.toHexString(0xff & messageDigest[i]);
              
                if(hex.length()==1) hexString.append('0');
              
                hexString.append(hex);
            }
            
            // Output
            System.out.println("Hashcode Generated : " + hexString.toString());

        } 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}