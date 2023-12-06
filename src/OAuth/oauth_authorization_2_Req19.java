import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

public class oauth_authorization_2_Req19 {
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".credentials/calendar-java-quickstart");
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    private static final com.google.api.client.json.JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static com.google.api.client.http.HttpTransport HTTP_TRANSPORT;

    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String... args) throws Exception {
        Credential credential = authorize();
        // Do something with the authorized credentials
    }

    public static Credential authorize() throws Exception {
        // Load client secrets.
        Reader clientSecretReader = new InputStreamReader(oauth_authorization_2_Req19.class.getResourceAsStream("/client_secret.json"));
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, clientSecretReader);

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
            .setDataStoreFactory(DATA_STORE_FACTORY)
            .setAccessType("offline")
            .build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }
}