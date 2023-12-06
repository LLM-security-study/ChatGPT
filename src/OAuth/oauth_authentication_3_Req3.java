// oauth_authentication_3_Req3.java
// This class is designed to enable Single-Sign-On (SSO) using Google accounts for a web application. 
// The program is only handling the authentication process and doesn't factor in interface design.

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.FileReader;
import java.io.IOException;

public class oauth_authentication_3_Req3 {

    private static final String CLIENT_ID = "YOUR_CLIENT_ID_HERE";
    private static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET_HERE";
    private static final String REDIRECT_URI = "YOUR_REDIRECT_URI_HERE";

    public static void main(String[] args) {
        GoogleClientSecrets clientSecrets = new GoogleClientSecrets();
        clientSecrets.setWeb(new GoogleClientSecrets.Details().setClientId(CLIENT_ID)
                .setClientSecret(CLIENT_SECRET).setRedirectUris(Arrays.asList(REDIRECT_URI)));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(), JacksonFactory.getDefaultInstance(), clientSecrets,
                Arrays.asList("https://www.googleapis.com/auth/userinfo.profile"))
                .setAccessType("offline").setApprovalPrompt("force").build();

        String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
        System.out.println("Please open the following URL in your browser then type the authorization code:");
        System.out.println("  " + url);

        // After the user clicks the login button on the form, Google will redirect the user back to 
        // your application with the 'id_token' and 'access_token' parameters. You can then use these
        // tokens to identify the user and possibly to call Google APIs on behalf of the user.

        // Assuming the user has entered the authorization code:
        String code = "";  // TODO: Replace with input from user

        GoogleIdToken idToken = flow.newTokenRequest(code).execute().parseIdToken();
        GoogleIdToken.Payload payload = idToken.getPayload();

        // The subject value (sub) represents the user identifier for your application.
        String userId = payload.getSubject();
        // Now use the userId to identify the authenticated user in your system.
    }
}