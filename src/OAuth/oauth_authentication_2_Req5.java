// oauth_authentication_2_Req5.java

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

public class oauth_authentication_2_Req5 {
    // Client ID and Client Secret should be filled, they are available from the Google Developer Console.
    private static final String CLIENT_ID = "your-client-id";
    private static final String CLIENT_SECRET = "your-client-secret";
    private static final String REDIRECT_URI = "your-redirect-uri";

    private static GoogleAuthorizationCodeFlow flow;

    public oauth_authentication_2_Req5() throws IOException {
        GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();
        web.setClientId(CLIENT_ID);
        web.setClientSecret(CLIENT_SECRET);
        web.setRedirectUris(Collections.singletonList(REDIRECT_URI));

        flow = new GoogleAuthorizationCodeFlow.Builder(
            new NetHttpTransport(), JacksonFactory.getDefaultInstance(), web, Collections.singleton("profile"))
            .build();
    }

    public String getAuthorizationUrl() {
        return flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
    }

    public GoogleIdToken.TokenInfo authenticate(String authorizationCode) {
        try {
            String token = flow.newTokenRequest(authorizationCode)
                .setRedirectUri(REDIRECT_URI).execute().getAccessToken();

            GoogleIdToken idToken = GoogleIdToken.parse(JacksonFactory.getDefaultInstance(), token);
            return idToken.getPayload();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}