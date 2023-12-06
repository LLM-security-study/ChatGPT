import java.nio.file.*;
import java.security.*;

public class hash_2_Req19{
    public static void main(String[] args){
        String filename = "<filename here>";
        String originalHash = "<original hash here>";

        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] byteData = Files.readAllBytes(Paths.get(filename)); 

            md.update(byteData);
            byte[] digestedData = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digestedData.length; i++) {
                sb.append(Integer.toString((digestedData[i] & 0xff) + 0x100, 16).substring(1));
            }

            String calculatedHash = sb.toString();

            if (originalHash.equals(calculatedHash)) {
                System.out.println("File is fine, hashes match.");
            } else {
                System.out.println("File has been modified, hashes do not match!");
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}