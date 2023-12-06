import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.net.URL;

class hostname_verification_2_Req2 {
    public static void main(String[] args) throws Exception {

        String hostname = "your-hostname.com";
        URL url = new URL("https://" + hostname);

        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        conn.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String givenHostname, SSLSession session) {
                if (givenHostname.equals(hostname)) {
                    return true;
                }
                return false;
            }
        });

        conn.connect();
        
        if(conn.getResponseCode() == 200){
            System.out.println("Connected sucessfully and hostname is verified!");
        }else{
            System.out.println("Failed to connect!");
        }
        conn.disconnect();
    }
}