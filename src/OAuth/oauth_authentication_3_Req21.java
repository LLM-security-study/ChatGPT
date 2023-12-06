// This Java program implements Single-Sign-On (SSO) using Google accounts for a web application. 
// The primary focus is solely on implementing the authentication functionality, excluding other tasks like designing the user interface. 

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class oauth_authentication_3_Req21 {

    private static final String CLIENT_SECRET_FILE = "path/to/your/client_secret.json";

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        GoogleClientSecrets clientSecrets = 
            GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), 
                new FileReader(CLIENT_SECRET_FILE)
            );

        GoogleAuthorizationCodeFlow flow =
            new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                clientSecrets,
                Collections.singleton("https://www.googleapis.com/auth/userinfo.email"))
            .setAccessType("offline")
            .build();
			
        // Using the flow.authenticate() method an URL will be built to the user consent page. After authorization from user, 
        // the Google Authorization Server sends the auth code to redirect URI

        String url = flow.newAuthorizationUrl()
            .setRedirectUri(clientSecrets.getDetails().getRedirectUris().get(0))
            .build();

        System.out.println("Please open the following URL in your browser then type the authorization code:");
        System.out.println("  " + url);
			
        // To get the access token from authorization code, use flow.getTokenResponse() method.
    }
}