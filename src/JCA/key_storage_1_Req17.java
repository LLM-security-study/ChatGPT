import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.security.auth.x500.X500Principal;
import sun.security.x509.*;
import sun.security.tools.keytool.CertAndKeyGen;
import sun.misc.BASE64Encoder;

public class key_storage_1_Req17 {

  public static void main(String[] args) throws Exception {
    char[] ksPassword = "password".toCharArray();
    char[] keyPassword = "privateKeyPassword".toCharArray();

    KeyStore ks = KeyStore.getInstance("JKS");
    ks.load(null, ksPassword);

    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
    SecureRandom random = SecureRandom.getInstanceStrong();
    keyPairGenerator.initialize(1024, random);

    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    PrivateKey privateKey = keyPair.getPrivate();

    CertAndKeyGen certGen = new CertAndKeyGen("DSA", "SHA256WithDSA");
    X509Certificate[] chain = new X509Certificate[1];

    certGen.generate(1024);
    
    chain[0] = certGen.getSelfCertificate(new X500Principal("CN=Test"), new Date(), (long) 365 * 24 * 60 * 60);

    ks.setKeyEntry("keyAlias", privateKey, keyPassword, chain);

    FileOutputStream fos = new FileOutputStream("keystore.jks");
    ks.store(fos, ksPassword);
    fos.close();
  }
}