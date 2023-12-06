import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

public class oauth_authorization_2_Req8 {

    private static final String APPLICATION_NAME = "Your Application Name";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private static final String CLIENT_ID = "YOUR_CLIENT_ID";
    private static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET";
    private static final String REDIRECT_URI = "YOUR_REDIRECT_URI";
    private static final String AUTH_URI = "https://accounts.google.com/o/oauth2/auth";
    private static final String TOKEN_URI = "https://oauth2.googleapis.com/token";
    
    private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/calendar.events");

    public static void main(String[] args) throws IOException {
        final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();

        GoogleClientSecrets clientSecrets = new GoogleClientSecrets()
                .setInstalled(new GoogleClientSecrets.Details()
                        .setClientId(CLIENT_ID)
                        .setClientSecret(CLIENT_SECRET)
                        .setAuthUri(AUTH_URI)
                        .setTokenUri(TOKEN_URI)
                        .setRedirectUris(Collections.singletonList(REDIRECT_URI)));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver())
                .authorize("user");

        System.out.println("User successfully authorized!");
    }
}