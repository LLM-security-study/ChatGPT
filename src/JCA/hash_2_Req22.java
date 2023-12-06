// FileIntegrityCheck.java 

import java.io.*;
import java.security.*;

public class FileIntegrityCheck {
    public static String getSHA256Checksum(String filename) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        FileInputStream fis = new FileInputStream(filename);
        byte[] dataBytes = new byte[1024];
        
        int nread = 0; 
        while ((nread = fis.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        };
        
        byte[] mdbytes = md.digest();
        
        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
    }
}

// hash_2_Req22.java
public class hash_2_Req22 {
    public static void main(String args[]) throws Exception {
        String filename = "test.txt";  // input your filename
        String actualFileHash = FileIntegrityCheck.getSHA256Checksum(filename);
        
        String providedHash = "123abc...";  // input your provided hash value
        System.out.println("Are the provided hash and actual file hash same? " + actualFileHash.equals(providedHash));
    }
}