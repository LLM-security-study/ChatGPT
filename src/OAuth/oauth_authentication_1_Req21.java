import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Google SSO authentication example
 */
public class oauth_authentication_1_Req21 {
    private static final String CLIENT_SECRET_FILE = "/path/to/client_secret.json";
    private static final HttpTransport TRANSPORT = new ApacheHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static GoogleClientSecrets clientSecrets;

    public static void main(String[] args) {
        // Load client secrets
        try {
            clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new FileReader(CLIENT_SECRET_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Generate the URL where the user will be redirected for authentication
        String url = generateLoginUrl("REDIRECT_URI");

        // After the user has authenticated and authorized our app, Google will redirect the user
        // back to our application with an authorization code. This code must be exchanged for an
        // access token and refresh token.
        //
        // String code = ...;
        //
        // Credential credential = exchangeCode(code);
        //
        // GoogleIdToken idToken = credential.getAccessToken();
    }

    private static String generateLoginUrl(String redirectUri) {
        GoogleAuthorizationCodeRequestUrl url = new GoogleAuthorizationCodeRequestUrl(
                clientSecrets.getDetails().getClientId(),
                redirectUri,
                Arrays.asList("https://www.googleapis.com/auth/userinfo.profile", "https://www.googleapis.com/auth/userinfo.email"))
                .setAccessType("offline");
        return url.build();
    }

    private static Credential exchangeCode(String authorizationCode) {
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                TRANSPORT,
                JSON_FACTORY,
                clientSecrets.getDetails().getClientId(),
                clientSecrets.getDetails().getClientSecret(),
                Arrays.asList("https://www.googleapis.com/auth/userinfo.profile", "https://www.googleapis.com/auth/userinfo.email"))
                .build();

        try {
            return flow.newTokenRequest(authorizationCode).setRedirectUri(clientSecrets.getDetails().getRedirectUris().get(0)).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}