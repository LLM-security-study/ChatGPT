import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.*;
import java.util.Scanner;

public class KDF_2_Req5 
{
    private static final String ALGORITHM = "PBEWithMD5AndDES";
    private static final String SALT = "12345678"; // 8-byte Salt
    private static final int ITERATION_COUNT = 100; // Number of iterations

    public static void main(String args[]) 
    {
        try 
        {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a password: ");
            String password = scanner.nextLine();
            scanner.close();
            
            byte[] salt = SALT.getBytes();
            
            // Create PBE parameter set
            PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, ITERATION_COUNT);
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

            System.out.println("Secret key generated: " + pbeKey);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}