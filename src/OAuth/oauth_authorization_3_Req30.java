// Import the required libraries
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class oauth_authorization_3_Req30 {

    // Define the required constants
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/contacts.readonly");
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    
    /**
    * Load client secrets.
    * @throws IOException If the credentials.json file does not exist
    */
    private static Credential getCredentials() throws IOException {
        InputStream in = oauth_authorization_3_Req30.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
            JSON_FACTORY, new InputStreamReader(in)
        );

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
            .setDataStoreFactory(
                new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
            .setAccessType("offline")
            .build();

        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize();
    }

    public static void main(String... args) throws IOException {
        getCredentials();
    }
}