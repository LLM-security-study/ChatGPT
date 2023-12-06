import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.people.v1.PeopleServiceScopes;

import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.security.GeneralSecurityException;

public class oauth_authorization_3_Req4 {

    private static final String APPLICATION_NAME = "Social Networking Application";
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private static final List<String> SCOPES = Arrays.asList(PeopleServiceScopes.CONTACTS_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    public static void main(String... args) throws IOException, GeneralSecurityException {
        
        final net.http.HttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, 
                                        new InputStreamReader(oauth_authorization_3_Req4.class.getResourceAsStream(CREDENTIALS_FILE_PATH)));

        // Create authorization code flow
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        // Prompt user for consent
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

        System.out.println("Authorization was successful.");
    }
}