/* 
    Google Single-Sign-On Authentication implementation for a web application.
    This program provides the authentication functionality by integrating
    the Google's OAuth 2.0 library. The User Interface design is not a concern in this module.
*/

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class oauth_authentication_1_Req3 {

    private static final String CLIENT_SECRET_FILE = "path_to_client_secrets.json";
   
    public static void main(String[] args) throws Exception {
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
            JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));
        
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            new NetHttpTransport(), JacksonFactory.getDefaultInstance(), clientSecrets,
            Arrays.asList("https://www.googleapis.com/auth/userinfo.profile", "https://www.googleapis.com/auth/userinfo.email"))
            .build();
        
        // Redirect the user to the authorization URL
        String url = flow.newAuthorizationUrl().setRedirectUri(clientSecrets.getDetails().getRedirectUris().get(0)).build();
        System.out.println("Please open the following URL in your Internet Browser and authorize the application:");
        System.out.println(url);

        //... Here, you need to handle the redirect and fetch the code parameter that Google sends to the redirect URI

        // Then you can exchange the code for tokens:
        // String code = ...
        // GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(clientSecrets.getDetails().getRedirectUris().get(0)).execute();
     
        // And verify the id token
        // GoogleIdToken idToken = GoogleIdToken.parse(JacksonFactory.getDefaultInstance(), response.getIdToken());
        // Payload payload = idToken.getPayload();
        
    }
}