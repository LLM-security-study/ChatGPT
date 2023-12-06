/**
 * Requires Google SignIn library for Java
 */

// Import necessary packages
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class oauth_authentication_3_Req11 {
    private static final String CLIENT_SECRET_FILE = "/path_to_client_secrets.json";
    private static final Collection<String> SCOPES = 
        Collections.singletonList("https://www.googleapis.com/auth/userinfo.email");
    private static final String CALLBACK_URI = "/path_to_your_redirect_uri";
    private static GoogleAuthorizationCodeFlow flow;

    static {
        try {
            InputStream in = oauth_authentication_3_Req11.class.getResourceAsStream(CLIENT_SECRET_FILE);
            GoogleClientSecrets clientSecrets = 
                GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));
            flow = new GoogleAuthorizationCodeFlow.Builder(
                    clientSecrets.getDetails().getClientId(),
                    clientSecrets.getDetails().getClientSecret(),
                    SCOPES)
                .setAccessType("offline")
                .setApprovalPrompt("force").build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void authenticate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
        String redirectURL = url.setRedirectUri(CALLBACK_URI).setAccessType("offline").build();
        resp.sendRedirect(redirectURL);
    }

    public static void getCode(String code) {
        try {
            GoogleAuthorizationCodeTokenRequest tokenRequest = flow.newTokenRequest(code);
            tokenRequest.setRedirectUri(CALLBACK_URI);
            Credential credential = tokenRequest.execute();
            System.out.println("Access token: " + credential.getAccessToken());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
