import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

/*
This Java program establishes Single-Sign-On (SSO) service for a web application 
using Google accounts for user authentication. 
*/

public class oauth_authentication_2_Req4 {
    // Assuming your redirectUri, clientId and clientSecret are constants
    private static final String REDIRECT_URI = "Enter Redirect URI";
    private static final String CLIENT_ID = "Enter Client ID";
    private static final String CLIENT_SECRET = "Enter Client Secret";

    public static void main(String[] args) throws Exception {
        // Create a new flow for handling steps in the OAuth 2.0 protocol
        AuthorizationCodeFlow flow = new AuthorizationCodeFlow.Builder(
                BearerToken.authorizationHeaderAccessMethod(),
                new NetHttpTransport(),
                new JacksonFactory(),
                new GenericUrl("https://server.example.com/token"),
                new BasicAuthentication(CLIENT_ID, CLIENT_SECRET),
                CLIENT_ID,
                "https://server.example.com/authorize").setCredentialDataStore(StoredCredential.getDefaultDataStore(new MemoryDataStoreFactory()))
                .build();

        // redirect to the authorization server
        // Send user to authorization URL
        AuthorizationCodeRequestUrl authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI);

        // User manually provides the authorization code
        String authorizationCode = ""; // ToDo: Get this from the User

        // Use the authorization code to get the access token
        Credential credential = flow.createAndStoreCredential(authorizationCode, "userID");

        // Print access token
        System.out.println("Access Token: " + credential.getAccessToken());
    }
}