import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger; 

public class hash_1_Req4
{
    public static String getMD5Hash(String input)
    {
        try 
        {
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 

            // Convert byte array into signum representation  
            BigInteger no = new BigInteger(1, messageDigest); 

            // Convert message digest into hex value  
            String hashtext = no.toString(16); 

            while (hashtext.length() < 32)
                hashtext = "0" + hashtext; 

            return hashtext; 
        }  
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e); 
        } 
    }

    public static void main(String args[]) throws NoSuchAlgorithmException 
    { 
        System.out.println("Hash Value: " + getMD5Hash("MyInputString"));  
    } 
}