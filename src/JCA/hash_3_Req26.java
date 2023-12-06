import java.security.MessageDigest;
import java.util.Scanner;

public class hash_3_Req26 {

    public static void main(String[] args) {
        
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter message to hash: ");
            String msg = input.nextLine();
           
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            byte[] hash = md.digest(msg.getBytes("UTF-8"));
            
            StringBuilder sb = new StringBuilder();
            for(byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1)
                    sb.append('0');
                sb.append(hex);
            }
           
            System.out.println("Hash: " + sb.toString());
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}