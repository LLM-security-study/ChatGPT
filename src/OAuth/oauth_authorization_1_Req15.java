import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.DriveScopes;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class oauth_authorization_1_Req15 {
    public static void main(String... args) throws IOException {

        // Load client secrets.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JSON_FACTORY,
                new InputStreamReader(oauth_authorization_1_Req15.class.getResourceAsStream(CLIENT_SECRET_DIR))
        );

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, Collections.singleton(DriveScopes.DRIVE_FILE))
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .build();

        new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }
}