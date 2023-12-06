import javax.crypto.*;
import java.security.*;
import java.io.*;
import java.util.*;

public class key_storage_1_Req2 {

    public static void main(String[] args) throws Exception {
        // Generate a key
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        SecretKey secretKey = keyGen.generateKey();

        // Create a keystore
        KeyStore keyStore = KeyStore.getInstance("JKS");

        // Load the keystore
        char[] password = "password".toCharArray();
        File file = new File("keystore.jks");

        if (file.exists()) {
            // If the file exists, load the keystore from the file
            FileInputStream fis = new FileInputStream(file);
            keyStore.load(fis, password);
            fis.close();
        } else {
            // If the file doesn't exist, load a blank keystore
            keyStore.load(null, password);
        }

        // Add the key to the keystore
        KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(secretKey);
        keyStore.setEntry("myKeyAlias", skEntry, new KeyStore.PasswordProtection(password));

        // Save the keystore
        FileOutputStream fos = new FileOutputStream("keystore.jks");
        keyStore.store(fos, password);
        fos.close();
    }
}