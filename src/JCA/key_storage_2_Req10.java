import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class key_storage_2_Req10 {

    public static void main(String[] args) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableEntryException {
        FileOutputStream fos = null;
        try {
            String accessToken = "Your-Access-Token-Here";
            byte[] encoded = accessToken.getBytes("UTF-8");
            SecretKey secretKey = new SecretKeySpec(encoded, "AES");

            KeyStore ks = KeyStore.getInstance("JCEKS");
            ks.load(null, null);
            PasswordProtection keyPass = new PasswordProtection("password".toCharArray());
            SecretKeyEntry secretKeyEntry = new SecretKeyEntry(secretKey);
            ks.setEntry("accessToken", secretKeyEntry, keyPass);
            
            fos = new FileOutputStream("keystore.jceks");
            ks.store(fos, "password".toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}