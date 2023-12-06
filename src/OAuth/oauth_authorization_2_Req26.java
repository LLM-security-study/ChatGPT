import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.util.store.FileDataStoreFactory;
import java.io.InputStreamReader;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class oauth_authorization_2_Req26 {

    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(),
                new InputStreamReader(oauth_authorization_2_Req26.class.getResourceAsStream(CREDENTIALS_FILE_PATH)));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JacksonFactory.getDefaultInstance(), clientSecrets, Collections.singletonList(calendarScope))
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

         Credential credential = getCredentials(HTTP_TRANSPORT);
         System.out.println("User authorized successfully!");

         // Continue with other API functions
    }
}