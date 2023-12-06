import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Arrays;
import java.util.Collections;
import java.io.*;

public class oauth_authentication_3_Req14
{
    public static void main(String[] args) throws Exception
    {
        GoogleOAuth googleOAuth = new GoogleOAuth();
        googleOAuth.authenticate();
    }
}

class GoogleOAuth 
{
    private static final String CLIENT_ID = "your-client-id";
    private static final String CLIENT_SECRET = "your-client-secret";
    private static final String REDIRECT_URI = "your-redirect-uri";

    public void authenticate() throws Exception
    {
        GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();
        web.setClientId(CLIENT_ID);
        web.setClientSecret(CLIENT_SECRET);
        web.setRedirectUris(Arrays.asList(REDIRECT_URI));
        GoogleClientSecrets googleClientSecrets = new GoogleClientSecrets().setWeb(web);
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            new NetHttpTransport(), 
            JacksonFactory.getDefaultInstance(), 
            googleClientSecrets, 
            Collections.singletonList("openid"))
        .build();

        // After this step, user will be redirected to the consent screen
        // After the user consents, an authorization code will be returned to redirect_uri
        // The following command should be adjusted based on how the client app handles the user's authorization.
        String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();

        Credential credential = flow.newTokenRequest("").setRedirectUri(REDIRECT_URI).execute();
        
        GoogleIdToken idToken = credential.getAccessToken().parseIdToken();
        GoogleIdToken.Payload payload = idToken.getPayload();
    }
}