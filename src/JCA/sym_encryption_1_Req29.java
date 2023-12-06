import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class sym_encryption_1_Req29 {
    
    private static SecretKey secretKey;
 
    public static void main(String[] args) throws Exception 
    { 
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // key size is 128
        secretKey = keyGenerator.generateKey();
        
        String plainText = "Hello, World!"; 
 
        System.out.println("Plain Text: " + plainText); 
 
        String encryptedText = encrypt(plainText);
        System.out.println("Encrypted Text: " + encryptedText); 
 
        String decryptedText = decrypt(encryptedText);
        System.out.println("Decrypted Text: " + decryptedText); 
        
    } 

    public static String encrypt(String plainText) throws Exception 
    { 
        Cipher aesCipher = Cipher.getInstance("AES"); 
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        
        byte[] bytePlainText = plainText.getBytes(); 
        byte[] byteCipherText = aesCipher.doFinal(bytePlainText); 
        
        // convert bytes to Hexadecimal to represent it as string
        StringBuilder sb = new StringBuilder();
        for (byte b : byteCipherText) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString(); 
    } 

    public static String decrypt(String cipherText) throws Exception 
    { 
        Cipher aesCipher = Cipher.getInstance("AES"); 
        aesCipher.init(Cipher.DECRYPT_MODE, secretKey);

        // convert Hexadecimal to bytes to decrypt it
        String[] ctBytes = cipherText.split(" ");
        byte[] ctByteArray = new byte[ctBytes.length];
        for (int i=0; i<ctBytes.length; i++) {
            ctByteArray[i] = (byte)Integer.parseInt(ctBytes[i].trim(), 16);
        }

        byte[] byteDecipherText = aesCipher.doFinal(ctByteArray);
       
        return new String(byteDecipherText);
    }    

}