import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.Calendar;

import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class oauth_authorization_2_Req10 {
    
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    public static void main(String... args) throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        GoogleClientSecrets clientSecrets;
        
        try (InputStream in = oauth_authorization_2_Req10.class.getResourceAsStream("/client_secrets.json");
             Reader reader = new InputStreamReader(in)) {
            clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, reader);
        }

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

        // Next, you can use the Calendar service with the authorized credential:
        // Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
    }
}