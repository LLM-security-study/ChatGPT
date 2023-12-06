//Implementing Single-Sign-On (SSO) service for a web application using Google
//accounts for user authentication

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class oauth_authentication_2_Req18 {
    // Replace these values with your client id, client secret and redirect URI obtained from Google Developer console
    private static final String CLIENT_ID = "your_client_id";
    private static final String CLIENT_SECRET = "your_client_secret";
    private static final String REDIRECT_URI = "your_redirect_URI";
    
    public static void main(String[] args) {
        GoogleClientSecrets clientSecrets = new GoogleClientSecrets()
                .setWeb(new GoogleClientSecrets.Details()
                        .setClientId(CLIENT_ID)
                        .setClientSecret(CLIENT_SECRET)
                        .setRedirectUris(Arrays.asList(REDIRECT_URI)));
        
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                clientSecrets,
                Arrays.asList("https://www.googleapis.com/auth/userinfo.profile", "https://www.googleapis.com/auth/userinfo.email")) 
                .setAccessType("offline").build();
        
        // Replace the authorization code with code obtained by authenticating the user
        String authorizationCode = "auth_code";
        
        try {
            GoogleTokenResponse response = flow.newTokenRequest(authorizationCode)
                    .setRedirectUri(REDIRECT_URI)
                    .execute();
        
            Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod());
            credential.setFromTokenResponse(response);
            
            // Now the user is authenticated and you can make Google API calls on behalf of this user
        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
        }
    }
}