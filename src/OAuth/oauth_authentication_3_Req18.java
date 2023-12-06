// oauth_authentication_3_Req18.java

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class oauth_authentication_3_Req18 {

    private static final String CALLBACK_URI = "http://localhost:8080/callback";
    private static final List<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile");
    
    private static GoogleClientSecrets clientSecrets;
    private static GoogleAuthorizationCodeFlow flow;
    private static Credential credential;

    public static void main(String[] args) {
      
      try {
        // load client secrets
        InputStream in = oauth_authentication_3_Req18.class.getResourceAsStream("/client_secrets.json");
        clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));
        
        // set up authorization code flow
        flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(), JacksonFactory.getDefaultInstance(), clientSecrets, SCOPES)
                .build();

        // trigger user authorization request
        AuthorizationCodeRequestUrl authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(CALLBACK_URI);
        System.out.println("Go to the following link in your browser:");
        System.out.println(authorizationUrl);
       
    
      } catch (IOException e) {
        e.printStackTrace();
      }

    }

}
