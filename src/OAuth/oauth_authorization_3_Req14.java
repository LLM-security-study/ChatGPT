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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class oauth_authorization_3_Req14 {

    private static final String APPLICATION_NAME = "Google Contacts API Java";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FOLDER = "credentials"; // Directory to store user credentials.

    /**
     * This method requests a set of scopes which indicate the permissions that your application wants to be granted.
     * For the purpose of this task, we need to use the `https://www.googleapis.com/auth/contacts.readonly`
     * readonly scope.
     */
    private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/contacts.readonly");

    /**
     * The `LoadClientSecrets` method attempts to load a set of client secrets from a file located in
     * your project's resources directory.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = oauth_authorization_3_Req14.class.getResourceAsStream(CREDENTIALS_FOLDER + "/credentials.json");
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FOLDER + "/credentials.json");
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build authorization flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
            .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
            .setAccessType("offline")
            .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Credential credentials = getCredentials(HTTP_TRANSPORT);

        //The program prints the credential's access token, just to verify everything works as expected.
        System.out.println("Credentials saved to " + "tokens" + " directory");
        System.out.println("Access Token: " + credentials.getAccessToken());
    }
}