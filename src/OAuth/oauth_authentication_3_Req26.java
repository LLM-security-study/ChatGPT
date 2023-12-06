import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Tokeninfo;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * This Java program is to enable Single-Sign-On (SSO) 
 * using Google accounts for a web application.
 */
public class oauth_authentication_3_Req26{
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Initialize a flow to administrative OAuth2 scopes.
     */
    public static GoogleAuthorizationCodeFlow initializeFlow() throws IOException, GeneralSecurityException {
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new FileReader("client_secret.json"));
        return new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
    }

    /**
     * Method to validate the user's token.
     */
    public static void processUserToken(String idTokenString) throws IOException, GeneralSecurityException {
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        final Oauth2 oauth2 = new Oauth2.Builder(HTTP_TRANSPORT, JSON_FACTORY, null)
                .setApplicationName("Your Application Name")
                .build();
        Tokeninfo tokenInfo = oauth2.tokeninfo().setIdToken(idTokenString).execute();

        if(!clientSecrets.getDetails().getClientId().equals(tokenInfo.getIssuedTo())) {
            throw new IllegalArgumentException("The token's client ID does not match the app's.");
        }
    }
}