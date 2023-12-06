import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class hash_3_Req8 {

    public static void main(String[] args) {
        try {
            System.out.println("Please input message:");
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = messageDigest.digest(message.getBytes());

            String hash = bytesToHex(encodedhash);
            System.out.println("Hash: " + hash);
            
            scanner.close();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    } 
    
    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}