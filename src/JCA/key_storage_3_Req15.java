import java.io.FileInputStream;
import java.io.FileOutputStream;
//import classes for key and certificate management
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;

public class key_storage_3_Req15 {

    public static void main(String[] args) throws Exception {

        //Check if all arguments are filled
        if (args.length < 5) {
            System.err.println("Please provide necessary arguments in the following format:");
            System.err.println("Argument Format: source_keystore_path target_keystore_path storepass alias keypass");
            System.exit(-1);
        }

        String srcKeystorePath = args[0];
        String tgtKeyStorePath = args[1];
        char[] storePass = args[2].toCharArray();
        String alias = args[3];
        char[] keyPass = args[4].toCharArray();
        
        FileInputStream srcKeystoreInputStream = new FileInputStream(srcKeystorePath);

        //Create and load source keystore
        KeyStore srcKeystore = KeyStore.getInstance("jks");
        srcKeystore.load(srcKeystoreInputStream, storePass);

        //Get key and certificate chain from source keystore
        Key key = srcKeystore.getKey(alias, keyPass);
        Certificate[] chain = srcKeystore.getCertificateChain(alias);
        
        //Create a new keystore and add key and certificate chain
        KeyStore tgtKeystore = KeyStore.getInstance("jks");
        // Note: This assumes that the keystore is empty. If that's not the case, you should load your existing keystore instead of creating a new empty one.
        tgtKeystore.load(null, null);
        tgtKeystore.setKeyEntry(alias, key, keyPass, chain);
        
        FileOutputStream fileOut = new FileOutputStream(tgtKeyStorePath);
        tgtKeystore.store(fileOut, storePass);

        //Close the keystoreInputStream and fileOut
        srcKeystoreInputStream.close();
        fileOut.close();

        System.out.println("SSL certificate stored in keystore successfully.");
    }
}