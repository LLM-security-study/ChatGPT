import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.*;
import java.security.GeneralSecurityException;

public class oauth_authorization_1_Req28 {
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private static GoogleAuthorizationCodeFlow getAuthorizationFlow(final NetHttpTransport httpTransport) throws IOException {
        // Load client secrets.
        InputStream in = oauth_authorization_1_Req28.class.getResourceAsStream("/credentials.json");

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        return new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, Collections.singletonList("https://www.googleapis.com/auth/drive.file"))
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
    }

    private static Credential getCredentials(final NetHttpTransport httpTransport) throws IOException {
        GoogleAuthorizationCodeFlow flow = getAuthorizationFlow(httpTransport);
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build a new HTTP Transport
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        // Get Credentials
        getCredentials(httpTransport);
    }
}