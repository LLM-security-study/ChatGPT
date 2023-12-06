import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/*
This Java program integrates Google Single-Sign-On (SSO) with a web application for user authentication. 
This program is limited to the implementation of the authentication functionality.
*/

public class oauth_authentication_1_Req22 {
    private static final String CLIENT_ID = "yourGoogleApiClientId";
    private static final String CLIENT_SECRET = "yourGoogleApiClientSecret";
    private static final List<String> SCOPE = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile;https://www.googleapis.com/auth/userinfo.email".split(";"));
    private static final String REDIRECT_URI = "yourRedirectUri";

    private static GoogleAuthorizationCodeFlow flow = null;

    protected static GoogleAuthorizationCodeFlow getFlow() {
        if (flow == null) {
            flow = new GoogleAuthorizationCodeFlow.Builder(
                    new NetHttpTransport(),
                    new JacksonFactory(),
                    CLIENT_ID, // Update with your app Id
                    CLIENT_SECRET, // Update with your app secret
                    SCOPE )
                    .setAccessType("online") 
                    .setApprovalPrompt("auto").build();
        } 
        return flow;
    }

    public static void authenticate(String authenticationCode) throws IOException {
        GoogleTokenResponse response = getFlow().newTokenRequest(authenticationCode).setRedirectUri(REDIRECT_URI).execute();
        Credential credential = getFlow().createAndStoreCredential(response, null);
    }
   
    public static String buildLoginUrl() {
        final GoogleAuthorizationCodeRequestUrl url = getFlow().newAuthorizationUrl();
        return url.setRedirectUri(REDIRECT_URI).build();
    }
}