import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.CalendarScopes;


import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

public class oauth_authorization_2_Req18 {
    private static final String APPLICATION_NAME = "Google Calendar API Java";
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);

    private static Credential getCredentials() throws Exception {
        // Load client secrets.
        Reader clientSecretReader = new InputStreamReader(oauth_authorization_2_Req18.class.getResourceAsStream("/client_secret.json"));
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), clientSecretReader);

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), clientSecrets, SCOPES)
                .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    public static void main(String... args) throws Exception {
        // Build a new authorized API client service.
        com.google.api.services.calendar.Calendar service =
                new com.google.api.services.calendar.Calendar.Builder(
                        GoogleNetHttpTransport.newTrustedTransport(),
                        JacksonFactory.getDefaultInstance(),
                        getCredentials())
                        .setApplicationName(APPLICATION_NAME)
                        .build();

        // Application logic goes here.
    }
}