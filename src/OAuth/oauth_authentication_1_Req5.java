/*
Program: Google Single-Sign-On Authentication for Web Application
Description: This program integrates Google Single-Sign-On for user authentication into a web application.
*/

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.FileReader;

public class oauth_authentication_1_Req5 {
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    // replace these with your values
    private static final String CLIENT_SECRET_FILE = "path/to/client_secret.json";
    private static final String REDIRECT_URI = "your redirect URI here";
    private static final String SCOPE = "https://www.googleapis.com/auth/userinfo.profile";

    public static void main(String[] args) throws Exception {
        // Load client secrets
        GoogleClientSecrets clientSecrets =
              GoogleClientSecrets.load(JSON_FACTORY, new FileReader(CLIENT_SECRET_FILE));

        // Build flow and trigger user authorization request
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(), JSON_FACTORY, clientSecrets, Collections.singleton(SCOPE))
                .setRedirectUri(REDIRECT_URI)
                .build();

        String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
        System.out.println("Please open the following URL in your browser and authorize the app:");
        System.out.println("  " + url);
    }
}