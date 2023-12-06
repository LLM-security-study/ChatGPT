// This Java program establishes a Single-Sign-On (SSO) service for a web application
// It uses Google accounts for user authentication 

import java.awt.Desktop;
import java.net.URI;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

public class oauth_authentication_2_Req7 {
    private static final String CLIENT_ID = "Your Client ID";
    private static final String CLIENT_SECRET = "Your Client Secret";
    private static final String REDIRECT_URI = "Your Redirect URI";

    public static void main(String[] args) {
        // Create a new Google Authorization Code Flow
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                CLIENT_ID,
                CLIENT_SECRET,
                Arrays.asList("openid", "email"))
                .setAccessType("offline")
                .setApprovalPrompt("force")
                .build();

        // Get the authorization URL and launch it in the default web browser
        String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI(url));
        }

        // Wait for the authorization code
        System.out.println("Enter the authorization code:");
        String code = new Scanner(System.in).nextLine();

        // Exchange the authorization code for tokens
        GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();

        // Print the access and refresh tokens
        System.out.println("Access token: " + response.getAccessToken());
        System.out.println("Refresh token: " + response.getRefreshToken());
    }
}