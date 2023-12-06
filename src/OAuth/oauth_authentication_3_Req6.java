// A Google SSO Authentication Example Java Program

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;

import java.io.InputStreamReader;
import java.util.Arrays;

public class oauth_authentication_3_Req6 {
    private static final String CLIENT_ID = "your-client-id";
    private static final String CLIENT_SECRET = "your-client-secret";
    private static final String REDIRECT_URI = "your-redirect-uri";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public static void main(String[] args) {
        try {
            final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, 
                new InputStreamReader(oauth_authentication_3_Req6.class.getResourceAsStream("/client_secrets.json")));
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    httpTransport, JSON_FACTORY, clientSecrets, 
                    Arrays.asList("openid", "email", "profile"))
                .setRedirectUri(REDIRECT_URI)
                .build();
            // authorize and authenticate the user 
            String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
            // print link for user to authorize the application to access their google account
            System.out.println("Please open the following URL in your browser and authorize the application:");
            System.out.println(" " + url);
            // the user authorization code obtained from the provided URL should be inputted next
          
            // ... omitted layers of authentication, including handling the callback from Google 

            Oauth2 oauth2 = new Oauth2.Builder(httpTransport, JSON_FACTORY, null)
                .setHttpRequestInitializer(flow.loadCredential(/*todo: provide user code*/)).build();
            Userinfo userinfo = oauth2.userinfo().get().execute();
            // print the full name of the user
            System.out.println(userinfo.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}