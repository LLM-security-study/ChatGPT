import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;

/*  
 * Single-Sign-On service that uses Google accounts for user authentication.
 * OAuth 2.0 protocol is used with Authorization Code Grant flow.
 */

public class oauth_authentication_2_Req26 {
    private static final String CLIENT_ID = "Your-Client-ID";
    private static final String CLIENT_SECRET = "Your-Client-Secret";
    private static final String REDIRECT_URI = "Your-Redirect-URI";

    public static void main(String[] args) throws IOException {
        
        // Initialize the Authorization Code Flow
        AuthorizationCodeFlow codeFlow = new AuthorizationCodeFlow.Builder(
            "https",
            "https://accounts.google.com",
            CLIENT_ID,
            CLIENT_SECRET,
            java.util.Collections.singleton("https://www.googleapis.com/auth/userinfo.profile"))
            .setTokenServerEncodedUrl("https://oauth2.googleapis.com/token")
            .setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .setAuthorizationServerEncodedUrl("https://accounts.google.com/o/oauth2/v2/auth")
            .setScopes(java.util.Collections.singleton("https://www.googleapis.com/auth/userinfo.profile")) 
            .build();

        // User Redirect for authentication
        AuthorizationCodeRequestUrl authorizationUrl = codeFlow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI);
        System.out.println("Go to the following link in your browser:");
        System.out.println(authorizationUrl);

        // TODO: Take the authorization code as user input

        // Request for access token
        AuthorizationCodeTokenRequest tokenRequest = codeFlow.newTokenRequest("The authorization code").setRedirectUri(REDIRECT_URI);
        TokenResponse tokenResponse = tokenRequest.execute();
        System.out.println("Access Token: "+ tokenResponse.getAccessToken());
    }
}