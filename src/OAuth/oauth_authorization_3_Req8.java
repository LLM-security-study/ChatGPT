import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

public class oauth_authorization_3_Req8 {

    private static final String REDIRECT_URI = "Enter Your Redirect URI here";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static GoogleClientSecrets clientSecrets;

    public static void main(String[] args) throws Exception {
        clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new FileReader("client_secrets.json"));

        AuthorizationCodeFlow authorizationCodeFlow = new GoogleAuthorizationCodeFlow.Builder(new NetHttpTransport(), JSON_FACTORY, clientSecrets, Collections.singleton("https://www.googleapis.com/auth/contacts.readonly"))
                .build();

        String url = authorizationCodeFlow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).setState("state").build();
        System.out.println("Please open the following URL in your browser, the authorize the app: " + url);

        // Wait for the authorization code
        System.out.println("Enter the authorization code you received here:");
        String code = new java.util.Scanner(System.in).nextLine();

        // Generate an authorized token
        authorizationCodeFlow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
    }
}