import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.CalendarScopes;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class oauth_authorization_2_Req2 {
    private static final String CLIENT_SECRET_DIR = "client_secret.json";
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    
    public static void main(String... args) {
        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Credential credential = authorize(HTTP_TRANSPORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static Credential authorize(final NetHttpTransport HTTP_TRANSPORT) throws Exception {
        InputStream in = oauth_authorization_2_Req2.class.getResourceAsStream(CLIENT_SECRET_DIR);
        
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CLIENT_SECRET_DIR);
        }
        
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .build();
                
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }
}