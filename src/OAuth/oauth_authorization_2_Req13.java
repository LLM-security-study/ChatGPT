// required imports
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

//oauth_authorization_2_Req13 class
public class oauth_authorization_2_Req13 {

    // The Client ID and Secret can be found on the Google API Console
    private static final String CLIENT_ID = "YOUR_CLIENT_ID";
    private static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET";
    // Define the required scopes
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
    // Define the JSON factory
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    // Define the path to the token directory
    private static final java.io.File TOKENS_DIRECTORY_PATH = new java.io.File("tokens");

    static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws Exception {
        // Load client secrets.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(new FileInputStream(CLIENT_ID + ".json")));

        // Build the authorization flow - GoogleAuthorizationCodeFlow
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(TOKENS_DIRECTORY_PATH))
                .setAccessType("offline").build();

        // Return the authorization code installed for the application
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    public static void main(String... args) throws Exception {
        // Initialize the transport
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        // Get the credential
        Credential c = getCredentials(HTTP_TRANSPORT);
    }
}