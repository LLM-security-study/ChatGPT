import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class hash_1_Req8
{
    public static void main(String[] args) throws NoSuchAlgorithmException 
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a string to calculate its hash value:"); 
        String originalString = in.nextLine();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
        System.out.println(bytesToHex(hash));
    }
    
    private static String bytesToHex(byte[] hash) 
    {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) 
        {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}