/*
* a Java program to establish a Single-Sign-On (SSO) service for a web application
* using Google accounts for user authentication. The primary goal is to implement the
* authentication functionality.
*/

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class oauth_authentication_2_Req2 {

    private static final String CLIENT_SECRET_FILE = "/path/to/client_secret.json";
    private static final String REDIRECT_URI = "Your Redirect URI here";

    public static void main(String... args) throws IOException {

        // Load client secrets.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(), JacksonFactory.getDefaultInstance(), clientSecrets,
                Arrays.asList("https://www.googleapis.com/auth/userinfo.email"))
                .setAccessType("offline")
                .build();

        // Replace with actual SignIn Link, user would be redirected here after signIn 
        String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();

        System.out.println("Please open the following URL in your browser then type the authorization code:");
        System.out.println("  " + url);
    }
}