import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.net.URL;
import javax.net.ssl.HostnameVerifier;

public class hostname_verification_2_Req16 
{
    public static void main(String[] args) 
    {
        try 
        {
            String urlString = "https://example.com";
            URL url = new URL(urlString);

            HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
            HostnameVerifier alternativeHostnameVerifier = new HostnameVerifier()
            {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true; 
                }
            };

            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setHostnameVerifier(new HostnameVerifier() 
            {
                @Override
                public boolean verify(String hostname, SSLSession session) 
                {
                    if (defaultHostnameVerifier.verify(hostname, session)) {
                        return true; 
                    } else if (alternativeHostnameVerifier.verify(hostname, session)) {
                        return true; 
                    } else {
                        return false; 
                    }
                }
            });
            
            urlConnection.connect();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}