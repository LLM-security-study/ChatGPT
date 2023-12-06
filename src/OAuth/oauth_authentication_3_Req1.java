import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;


public class oauth_authentication_3_Req1 {

    private static final String CLIENT_SECRET_FILE = "/path/to/your/client_secret.json";
    private static final String REDIRECT_URI = "Your redirect URI";

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        // load client secrets 
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
            JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));

        // build Authorization flow 
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            new NetHttpTransport(), JacksonFactory.getDefaultInstance(), clientSecrets,
            Collections.singleton("openid"))
            .build();

        // trigger user authorization via browser 
        String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();

        // The user will be redirected to REDIRECT_URI after user consent, 
        // your web app should extract the authorization code from the GET request and 
        // exchange it for a id token 
    }

    private static GoogleIdToken getIdToken(String authCode) throws IOException {
        // (Receive authCode by HTTPS POST)

        GoogleTokenResponse tokenResponse = 
            flow
                .newTokenRequest(authCode)
                .setRedirectUri(REDIRECT_URI)
                .execute();
        
        GoogleIdToken idToken = tokenResponse.parseIdToken();
        return idToken;
    }

}