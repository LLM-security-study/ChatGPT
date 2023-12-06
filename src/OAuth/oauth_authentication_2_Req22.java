/**
 * Java program to establish a Single-Sign-On (SSO) service for a web application, 
 * using Google accounts for user authentication.
 * 
 * This program includes: Google Sign-in for Websites (OAuth 2.0)
 * The application have been already registered with the Google service provider, 
 * and the necessary information (e.g., redirect URI, client ID, client secret has been obtained.)
**/

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.FileReader;
import java.io.IOException;

public class oauth_authentication_2_Req22 {
    private static final String REDIRECT_URI = "<Redirect-URI>";
    private static final String CLIENT_SECRET_JSON = "<Client-Secret-JSON>";

    public static void main(String[] args) throws IOException {
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
            JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_JSON));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            new NetHttpTransport(), JacksonFactory.getDefaultInstance(), clientSecrets, 
            Arrays.asList("https://www.googleapis.com/auth/userinfo.profile"))
            .setAccessType("offline").setApprovalPrompt("force").build();

        String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
        System.out.println("Authenticate user by navigating to the following URL: \n" + url);

        // Retrieve the authorization code from user and exchange it for access and refresh tokens.
        String code = "<User-Provided-Code>"

        GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();

        System.out.println("Access Token: " + response.getAccessToken());
        System.out.println("Refresh Token: " + response.getRefreshToken());
    }
}