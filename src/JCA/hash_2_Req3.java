import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_2_Req3 {
    public static void main(String[] args) {
        String filePath = args[0];
        String hashValue = args[1];
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            FileInputStream fis = new FileInputStream(filePath);
            byte[] dataBytes = new byte[1024];
            int nread = 0; 
                
            while ((nread = fis.read(dataBytes)) != -1) {
              md.update(dataBytes, 0, nread);
            };

            byte[] mdbytes = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mdbytes.length; i++) {
              sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            System.out.println("Hash code of file:");
            System.out.println(sb.toString());
        
            if (sb.toString().equals(hashValue)) {
                System.out.println("The hash codes match. The file is unaltered.");
            } else {
                System.out.println("The hash codes do not match. The file has been altered.");
            }

        fis.close(); 

        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}