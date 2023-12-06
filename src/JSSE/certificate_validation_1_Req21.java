import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.Certificate;
import java.net.MalformedURLException;
import java.io.IOException;

public class certificate_validation_1_Req21 {

  public static void main(String[] args)
  {
    new certificate_validation_1_Req21().testIt();
  }

  private void testIt(){

     String https_url = "https://www.google.com/";
     URL url;
     try {

	    url = new URL(https_url);
	    HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();

	    //get the server's certificate chain
	    Certificate[] certs = conn.getServerCertificates();
	    
	    System.out.println("Server's Certificate: ");
	    for(Certificate cert : certs){
	      System.out.println("Cert Type : " + cert.getType());
	      System.out.println("Cert Hash Code : " + cert.hashCode());
	      System.out.println("Cert Public Key Algorithm : " + cert.getPublicKey().getAlgorithm());
	      System.out.println("Cert Public Key Format : " + cert.getPublicKey().getFormat());

	       System.out.println("\n");
	    }
            
     } catch (MalformedURLException e) {

	    e.printStackTrace();

	 } catch (SSLPeerUnverifiedException e) {

	    e.printStackTrace();

	 } catch (IOException e){
     
	    e.printStackTrace();

	 }

    }

}