import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.FileReader;
import java.io.IOException;

/* A program to enable Single Sign-On (SSO) using Google accounts. 
 * This program is designed to provide users the ability to log in by using their Google account credentials. 
 * It uses Google's OAuth2 functionality to authenticate the user and then retrieve user information on successful authentication.
 */

public class oauth_authentication_3_Req25 {
    private static final String CLIENT_SECRET_FILE = "/path/to/your/client_secret.json";
    private static final String REDIRECT_URI = "your registered redirect URI";

    public static void main(String[] args) throws IOException {
        // Load client secrets
        GoogleClientSecrets secrets = GoogleClientSecrets.load(
		        JacksonFactory.getDefaultInstance(), 
		        new FileReader(CLIENT_SECRET_FILE));
        
        // Set up authorization code flow
        AuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
		        new NetHttpTransport(), 
		        JacksonFactory.getDefaultInstance(), 
		        secrets, 
		        // Request user's email address
		        Collections.singleton("https://www.googleapis.com/auth/userinfo.email"))
		        .setAccessType("online")
		        .setApprovalPrompt("auto").build();

        // Build the authorization URL
        String url = flow.newAuthorizationUrl()
		        .setRedirectUri(REDIRECT_URI)
		        .setAccessType("offline")
		        .build();
        
        // Redirect user to the authorization URL
        response.sendRedirect(url);
  
        // Await the callback on the redirect URI, then exchange the authorization code for a token
        String code = request.getParameter("code");
        TokenResponse tokenResponse = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();

        // Extract the access token.
        String accessToken = tokenResponse.getAccessToken();
    }
}