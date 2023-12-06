import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Establishes a Single-Sign-On (SSO) service for a web application,
 * using Google accounts for user authentication.
 */
public class oauth_authentication_2_Req3 {

    private static final String clientId = "YOUR_CLIENT_ID";
    private static final String clientSecret = "YOUR_CLIENT_SECRET";
    private static final String redirectUri = "YOUR_REDIRECT_URI";

    public static void main(String[] args) throws IOException {
        AuthorizationCodeFlow flow = new AuthorizationCodeFlow.Builder(
                        "https", new NetHttpTransport(), new JacksonFactory(),
                        "https://oauth2.googleapis.com/token", // token server encoded URL
                        "https://accounts.google.com/o/oauth2/auth", // authorization server encoded URL
                        clientId,
                        clientSecret)
                .setScopes(Arrays.asList("openid", "email", "profile")) // replace with your actual scopes
                .build();

        // Step 1: Direct user to the authentication URL
        String url = flow.newAuthorizationUrl().setRedirectUri(redirectUri).setState("xyz").build();
        System.out.println("Please open the following URL in your browser, and authorize the request:");
        System.out.println(url);

        // Step 2: User authorized the request and the browser has been redirected back. Let's get that authorization code.
        System.out.println("Enter the code you received:");
        Scanner scanner = new Scanner(System.in);
        String authCode = scanner.nextLine();

        // Step 3: Use the exchanged code to get the access token
        TokenResponse response = flow.newTokenRequest(authCode).setRedirectUri(redirectUri).execute();

        System.out.printf("Access token: %s", response.getAccessToken());
    }
}