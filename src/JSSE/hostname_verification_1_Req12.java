import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

public class hostname_verification_1_Req12 {
   public static void main(String[] args) {        
        try{
            URL url = new URL("https://www.example.com");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.connect();
            
            SSLSession sslSession = conn.getSSLSession();
            if(sslSession.isValid())
            {
                String hostname = url.getHost();
                verifyHostname(hostname, sslSession);
            }
            else
            {
                System.out.println("Invalid SSL Session");
            }
            conn.disconnect();              
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void verifyHostname(String hostname, SSLSession session) throws SSLPeerUnverifiedException {
        javax.net.ssl.HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
        boolean result = hv.verify(hostname, session);
        if(result){
            System.out.println("Hostname verification successful");
        }else{
            System.out.println("Hostname verification unsuccessful");
        }
    }
}