import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Arrays;
import java.util.Scanner;


/**
* Google SSO Implementation
*
* This is the Google Single Sign-On (SSO) service implementation using Google user authentication.
*/


public class oauth_authentication_2_Req9 {
    private static final String CLIENT_ID = "<CLIENT_ID>";
    private static final String CLIENT_SECRET = "<CLIENT_SECRET>";
    private static final String REDIRECT_URI = "<REDIRECT_URI>";

    public static void main(String[] args) throws Exception {
        GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();
        web.setClientId(CLIENT_ID);
        web.setClientSecret(CLIENT_SECRET);
        web.setRedirectUris(Arrays.asList(REDIRECT_URI));
        GoogleClientSecrets googleClientSecrets = new GoogleClientSecrets().setWeb(web);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                googleClientSecrets,
                Arrays.asList("profile", "email"))
                .setAccessType("offline")
                .build();
                
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please visit the following URL to authorize the application:");
        System.out.println(flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build());

        System.out.println("Enter the authorization code:");
        String code = scanner.nextLine();

        GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();

        Credential credential = flow.createAndStoreCredential(response, "user");

        System.out.println("Successfully signed in as: " + credential.getAccessToken());
    }
}