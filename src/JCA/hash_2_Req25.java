import java.io.*;
import java.security.*;

public class hash_2_Req25 {
    public static void main(String[] args){
        try {
            File file = new File("file.txt"); // replace with your actual file path

            String providedHashValue = "5D41402ABC4B2A76B9719D911017C592"; // replace with your actual provided hash value

            String fileHashValue = calculateHash(file);

            if (providedHashValue.equals(fileHashValue)) {
                System.out.println("File is not modified.");
            } else {
                System.out.println("File is modified.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String calculateHash(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
        FileInputStream fis = new FileInputStream(file);

        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        while ((bytesCount = fis.read(byteArray)) != -1) {
            sha256Digest.update(byteArray, 0, bytesCount);
        }

        fis.close();

        byte[] bytes = sha256Digest.digest();

        // This will convert bytes[] to hex
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}