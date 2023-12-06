import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash_1_Req5 {
  public static void main(String[] args) {
    String input = "Hello World";  // any string input
    System.out.println("Input String: " + input);
    String hashValue = "";
        
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");

      byte[] bytesHash = md.digest(input.getBytes());
      hashValue = convertByteArrayToHexString(bytesHash);
      System.out.println("Hash Value: " + hashValue);
            
    } catch (NoSuchAlgorithmException ex) {
      System.out.println("Error in hashing");
    }
  }

  private static String convertByteArrayToHexString(byte[] arrayBytes) {
    StringBuffer stringBuffer = new StringBuffer();
    for (int i = 0; i < arrayBytes.length; i++) {
      stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                .substring(1));
    }
    return stringBuffer.toString();
  }
}