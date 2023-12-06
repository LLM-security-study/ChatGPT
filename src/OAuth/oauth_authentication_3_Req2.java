/*
 * A Java program to enable Single-Sign-On (SSO) using Google's OAuth 2.0 authentication
 * for a web application. This is simplified implementation, focused solely on the authentication functionality.
 */

// Required imports
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class oauth_authentication_3_Req2 {
    // Define your own constants
    private static final String CLIENT_SECRETS_FILEPATH = "path/to/your/client_secrets.json";
    private static final String REDIRECT_URI = "your registered redirect URI";
    private static final String USER_ID = "user ID to authorize";

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        // Load client secrets
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
            JacksonFactory.getDefaultInstance(),
            new FileReader(CLIENT_SECRETS_FILEPATH)
        );

        // Build flow
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                clientSecrets,
                singletonList("https://www.googleapis.com/auth/userinfo.profile") // requesting user profile scope
        ).setRedirectUri(REDIRECT_URI).build();

        // Trigger authorization request and receive authorization URL
        String authorizationUrl = flow.newAuthorizationUrl()
            .setRedirectUri(REDIRECT_URI)
            .set("access_type", "offline") // Enable to get a refresh token
            .set("prompt", "consent") // Show user consent window every time
            .build();

        // Redirect user to authorizationUrl
        System.out.println("Please open the following URL in your browser then type the authorization code:");
        System.out.println("  " + authorizationUrl);

        // After user authorized, you will get a code (manually process in this example)
        // In real application, this is automated via Redirect URI
        String code = "the authorization code you got after user authorized";

        // Transform the authorization code into a token
        Credential credential = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();

        // Now you can use the credential to access Google APIs with this user permission
        // For example, to get user info
        // ...
    }
}