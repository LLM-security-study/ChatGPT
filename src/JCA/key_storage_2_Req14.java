import java.io.FileOutputStream;
import java.security.*;
import javax.security.cert.*;

public class key_storage_2_Req14 {
    public static void main(String[] args) {
        try {
            //Creating the KeyStore object
            KeyStore keyStore = KeyStore.getInstance("JKS");

            //Loading the KeyStore object
            char[] keyStorePassword = "keystorePassword".toCharArray();
            keyStore.load(null, keyStorePassword);

            //Creating the Key pair generator object
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            //Initializing the key pair generator
            keyPairGenerator.initialize(2048);

            //Generating the pair of keys
            KeyPair pair = keyPairGenerator.generateKeyPair();

            //Creating a X509 certificate
            Certificate[] chain = new Certificate[1];
            chain[0] = Certificate.getInstance("X.509");

            //Creating the KeyStore.ProtectionParameter object
            KeyStore.PasswordProtection passwordProtection = new KeyStore.PasswordProtection(keyStorePassword);

            //Creating the PrivateKeyEntry object
            KeyStore.PrivateKeyEntry privateKeyEntry = new KeyStore.PrivateKeyEntry(pair.getPrivate(), chain);

            //Setting the entry into keyStore
            keyStore.setEntry("accessToken", privateKeyEntry, passwordProtection);

            //Storing the KeyStore into the file
            FileOutputStream outputStream;
            outputStream = new FileOutputStream("keystore.jks");
            keyStore.store(outputStream, keyStorePassword);

            outputStream.close();
            System.out.println("Your accesstoken has been stored in a Keystore");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}