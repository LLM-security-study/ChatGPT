import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.util.Scanner; 
  
public class hash_1_Req23 { 
  
    private static String calculateHash(String input) { 
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); 
            byte[] messageDigest = md.digest(input.getBytes()); 

            StringBuilder hash = new StringBuilder();
            for (byte b : messageDigest) {
                hash.append(String.format("%02x", b));
            }
            return hash.toString();
        }

        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
  
    public static void main(String args[]) throws NoSuchAlgorithmException { 
        System.out.println("Enter the string: "); 
        Scanner sc = new Scanner(System.in); 

        String s = sc.nextLine(); 
        System.out.println("The hash value of " + s + " is : " + calculateHash(s)); 
    } 
}