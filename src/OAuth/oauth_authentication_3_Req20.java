/*
 * This is a Java program that implements SSO (Single Sign-On) using Google's OAuth 2.0 protocol for a web application.
 * For this example, let's assume the web application is already registered as required by Google.
 */

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class oauth_authentication_3_Req20 {
    private static final String CLIENT_SECRET_LOCATION = "client_secrets.json";
    private static final Collection<String> SCOPES = Arrays.asList("openid", "email");

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        GoogleClientSecrets secrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_LOCATION));
        String clientId = secrets.getDetails().getClientId();
        String clientSecret = secrets.getDetails().getClientSecret();

        GoogleAuthorizationCodeFlow flow =
            new GoogleAuthorizationCodeFlow.Builder(
                    new NetHttpTransport(),
                    JacksonFactory.getDefaultInstance(),
                    clientId,
                    clientSecret,
                    SCOPES)
                .build();
        
        // After building the flow, direct the user's browser to the returned redirect URL
        String redirectUrl = flow.newAuthorizationUrl().setRedirectURI("YOUR_CALLBACK_URL").build();

        // After the user authenticates, they are redirected back with an authorization code as a query parameter
        String authorizationCode = /* retrieve authorization code from HTTP request */;

        // Use the authorization code to get an access token
        GoogleTokenResponse tokenResponse = flow.newTokenRequest(authorizationCode).setRedirectUri("YOUR_CALLBACK_URL").execute();

        // Use the access token to get the user's information
        Payload payload = tokenResponse.parseIdToken().getPayload();

        // The email address is in the 'email' field and the user ID is in the 'sub' field
        String email = payload.getEmail();
        String userId = payload.getSubject();
    }
}