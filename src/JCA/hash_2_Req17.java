import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;

class hash_2_Req17{

    public static void main(String[] args) throws Exception{
        // Make sure the arguments have been passed.
        if(args.length < 1){
            System.err.println("Usage: java hash_2_Req17 <file_path> <known_hash>");
            System.exit(1);
        }

        String filePath = args[0];
        String knownHash = args[1];
        
        try {
            String fileHash = calculateHash(filePath);
            boolean result = compareHashes(fileHash, knownHash);
            System.out.println("Hashes are identical? " + result);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static String calculateHash(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        byte[] data = Files.readAllBytes(path);
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(data); 
        
        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, hash);
  
        // Convert message digest into hex value
        String hashtext = no.toString(16); 
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext; 
        } 
        return hashtext;
    }

    private static boolean compareHashes(String fileHash, String knownHash) {
        return fileHash.equals(knownHash);
    }
}