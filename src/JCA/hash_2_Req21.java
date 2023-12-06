import java.nio.file.*;
import java.security.*;
import java.io.*;

public class hash_2_Req21 {

    public static void main(String[] args) {
        String filePath = "<file_path>"; //replace with your file path
        String initialHash = "<initial_hash_value>"; //replace with the hash value you have

        try {
            boolean isSame = checkFileIntegrity(filePath, initialHash);
            if (isSame) {
                System.out.println("The file is not modified.");
            } else {
                System.out.println("The file is modified.");
            }

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    public static boolean checkFileIntegrity(String filePath, String initialHash) throws Exception {
        byte[] buffer = Files.readAllBytes(Paths.get(filePath));
        MessageDigest shaDigest = MessageDigest.getInstance("SHA-256");
        byte[] fileHash = shaDigest.digest(buffer);

        StringBuilder sb = new StringBuilder();
        for (byte b : fileHash) {
            sb.append(String.format("%02x", b));
        }
        String calculatedHash = sb.toString();

        return calculatedHash.equals(initialHash);
    }
}