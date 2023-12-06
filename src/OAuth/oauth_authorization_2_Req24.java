import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class oauth_authorization_2_Req24 {

    // TODO: Replace these with your own client ID and client secret
    private static final String CLIENT_ID = "your-client-id";
    private static final String CLIENT_SECRET = "your-client-secret";

    private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";

    public static void main(String[] args) {
        GoogleClientSecrets clientSecrets = new GoogleClientSecrets()
                .setInstalled(new GoogleClientSecrets.Details()
                        .setClientId(CLIENT_ID)
                        .setClientSecret(CLIENT_SECRET));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                clientSecrets,
                Arrays.asList("https://www.googleapis.com/auth/calendar"))
                .setAccessType("offline")
                .setApprovalPrompt("force")
                .build();

        String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
        System.out.println("Please open the following URL in your browser and authorize the app:");
        System.out.println("  " + url);

        try {
            System.out.print("Enter the authorization code: ");
            String code = new BufferedReader(new InputStreamReader(System.in)).readLine();

            GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
            System.out.println("Access Token: " + response.getAccessToken());

            // TODO: Store the access token and use it to access the Google Calendar API
            // Note: If the access token expires, you can use the refresh token to get a new one
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}